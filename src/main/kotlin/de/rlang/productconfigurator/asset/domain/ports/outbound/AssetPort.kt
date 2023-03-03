package de.rlang.productconfigurator.asset.domain.ports.outbound

import de.rlang.productconfigurator.asset.domain.model.Asset
import org.springframework.http.codec.multipart.FilePart

interface AssetPort {
    fun persistAsset(name: String, file: FilePart): Asset
    fun getAsset(assetId: Long): Asset?
}