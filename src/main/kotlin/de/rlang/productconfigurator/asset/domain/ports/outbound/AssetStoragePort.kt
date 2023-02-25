package de.rlang.productconfigurator.asset.domain.ports.outbound

import de.rlang.productconfigurator.asset.domain.model.Asset
import org.springframework.http.codec.multipart.FilePart

interface AssetStoragePort {
    fun persistAsset(name: String, file: FilePart): Asset
}