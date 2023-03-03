package de.rlang.productconfigurator.asset.domain.service

import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.ports.inbound.UploadAssetUseCase
import de.rlang.productconfigurator.asset.domain.ports.outbound.AssetPort
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service

@Service
class UploadAssetService(val assetPort: AssetPort) : UploadAssetUseCase {
    override fun uploadAsset(name: String, file: FilePart): Asset {
        return assetPort.persistAsset(name, file)
    }
}