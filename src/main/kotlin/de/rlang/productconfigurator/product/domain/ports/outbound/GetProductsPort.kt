package de.rlang.productconfigurator.product.domain.ports.outbound

import de.rlang.productconfigurator.product.domain.model.Product

interface GetProductsPort {
    suspend fun getProducts(): List<Product>
}