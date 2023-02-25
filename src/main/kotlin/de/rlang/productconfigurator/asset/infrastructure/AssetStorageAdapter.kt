package de.rlang.productconfigurator.asset.infrastructure

import de.rlang.productconfigurator.asset.configuration.AssetStorageConfiguration
import de.rlang.productconfigurator.asset.domain.entity.AssetEntity
import de.rlang.productconfigurator.asset.domain.mapper.toAssetModel
import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.model.AssetType
import de.rlang.productconfigurator.asset.domain.ports.outbound.AssetStoragePort
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Component
import java.net.URI
import kotlin.io.path.Path

@Component
class AssetStorageAdapter(
    val assetRepository: AssetRepository,
    val assetStorageConfiguration: AssetStorageConfiguration
) : AssetStoragePort {

    override fun persistAsset(name: String, file: FilePart): Asset {
        writeToDisk(file)
        val location = constructLocation(file.filename())
        var assetEntity = AssetEntity(null, name, location.toString(), AssetType.Environment)
        return toAssetModel(assetRepository.save(assetEntity))
    }

    fun constructLocation(fileName: String): URI =
        URI.create("${assetStorageConfiguration.serverURL}/$fileName")

    fun writeToDisk(file: FilePart) {
        file.transferTo(Path(assetStorageConfiguration.storagePath, file.filename()))
    }
}