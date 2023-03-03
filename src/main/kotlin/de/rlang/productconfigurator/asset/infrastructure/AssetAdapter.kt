package de.rlang.productconfigurator.asset.infrastructure

import de.rlang.productconfigurator.asset.configuration.AssetStorageConfiguration
import de.rlang.productconfigurator.asset.domain.entity.AssetEntity
import de.rlang.productconfigurator.asset.domain.mapper.toAssetModel
import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.model.AssetType
import de.rlang.productconfigurator.asset.domain.ports.outbound.AssetPort
import de.rlang.productconfigurator.unwrap
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.runBlocking
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Component
import java.net.URL
import kotlin.io.path.Path

@Component
class AssetAdapter(
    val assetRepository: AssetRepository,
    val assetStorageConfiguration: AssetStorageConfiguration
) : AssetPort {

    override fun persistAsset(name: String, file: FilePart): Asset {
        runBlocking { writeToDisk(file) }
        val location = constructLocation(file.filename())
        var assetEntity = AssetEntity(null, file.filename(), location, AssetType.Environment)
        return toAssetModel(assetRepository.save(assetEntity))
    }

    override fun getAsset(assetId: Long): Asset? =
        assetRepository.findById(assetId).map(::toAssetModel).unwrap()

    fun constructLocation(fileName: String): String =
        URL(URL(assetStorageConfiguration.serverURL), fileName).toString()

    suspend fun writeToDisk(file: FilePart) {
        val path = Path(assetStorageConfiguration.storagePath, file.filename()).toAbsolutePath()
        file.transferTo(path).awaitSingleOrNull()
    }
}