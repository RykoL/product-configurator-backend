package de.rlang.productconfigurator.asset.domain.model

import java.net.URI

enum class AssetType {
    Environment
}

data class Asset(
    val id: Long,
    val name: String,
    val location: URI,
    val assetType: AssetType
)