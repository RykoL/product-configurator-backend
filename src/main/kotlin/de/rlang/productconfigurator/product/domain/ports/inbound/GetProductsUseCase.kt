package de.rlang.productconfigurator.product.domain.ports.inbound

import de.rlang.productconfigurator.product.domain.model.Product

interface GetProductsUseCase {
    suspend fun getProducts(): List<Product>
}