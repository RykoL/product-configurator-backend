package de.rlang.productconfigurator.product.domain.model

data class Asset(
    val name: String,
    val path: String,
    val type: AssetType
)