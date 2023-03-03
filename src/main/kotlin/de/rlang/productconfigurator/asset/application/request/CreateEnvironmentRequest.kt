package de.rlang.productconfigurator.asset.application.request

data class CreateEnvironmentRequest(
    val name: String,
    val radius: Int,
    val height: Int,
    val assetId: Long
)
