package de.rlang.productconfigurator.product.domain.model

class Product(
    val id: Long,
    val name: String,
    val assets: List<Asset>
)