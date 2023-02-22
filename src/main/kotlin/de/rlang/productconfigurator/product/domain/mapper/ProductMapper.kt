package de.rlang.productconfigurator.product.domain.mapper

import de.rlang.productconfigurator.product.domain.entity.ProductEntity
import de.rlang.productconfigurator.product.domain.model.Product

fun toDomain(product: ProductEntity): Product = Product(
    product.id,
    product.name,
    emptyList()
)