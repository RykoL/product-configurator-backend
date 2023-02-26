package de.rlang.productconfigurator.asset.infrastructure

import arrow.core.Either
import de.rlang.productconfigurator.asset.configuration.AssetStorageConfiguration
import de.rlang.productconfigurator.asset.domain.InfrastructureError
import de.rlang.productconfigurator.asset.domain.entity.AssetEntity
import de.rlang.productconfigurator.asset.domain.mapper.toAssetModel
import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.model.AssetType
import de.rlang.productconfigurator.asset.domain.ports.outbound.AssetStoragePort
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.runBlocking
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Component
import reactor.kotlin.core.publisher.toMono
import java.net.URI
import java.net.URL
import kotlin.io.path.Path

@Component
class AssetStorageAdapter(
    val assetRepository: AssetRepository,
    val assetStorageConfiguration: AssetStorageConfiguration
) : AssetStoragePort {

    override fun persistAsset(name: String, file: FilePart): Asset {
        runBlocking { writeToDisk(file) }
        val location = constructLocation(file.filename())
        var assetEntity = AssetEntity(null, name, location, AssetType.Environment)
        return toAssetModel(assetRepository.save(assetEntity))
    }

    fun constructLocation(fileName: String): String =
        URL(URL(assetStorageConfiguration.serverURL), fileName).toString()

    suspend fun writeToDisk(file: FilePart) {
        val path = Path(assetStorageConfiguration.storagePath, file.filename()).toAbsolutePath()
        file.transferTo(path).awaitSingleOrNull()
    }
}