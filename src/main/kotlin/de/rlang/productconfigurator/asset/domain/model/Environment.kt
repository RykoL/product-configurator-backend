package de.rlang.productconfigurator.asset.domain.model

data class Environment(
    val id: Long,
    val name: String,
    val radius: Int,
    val height: Int,
    val asset: Asset
)
