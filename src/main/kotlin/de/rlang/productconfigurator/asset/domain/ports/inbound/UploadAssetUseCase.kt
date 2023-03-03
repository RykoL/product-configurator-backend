package de.rlang.productconfigurator.asset.domain.ports.inbound

import de.rlang.productconfigurator.asset.domain.model.Asset
import org.springframework.http.codec.multipart.FilePart

interface UploadAssetUseCase {
    fun uploadAsset(name: String, file: FilePart): Asset
}