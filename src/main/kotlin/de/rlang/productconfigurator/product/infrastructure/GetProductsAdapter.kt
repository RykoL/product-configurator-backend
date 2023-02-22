package de.rlang.productconfigurator.product.infrastructure

import de.rlang.productconfigurator.product.domain.mapper.toDomain
import de.rlang.productconfigurator.product.domain.model.Product
import de.rlang.productconfigurator.product.domain.ports.outbound.GetProductsPort
import org.springframework.stereotype.Component

@Component
class GetProductsAdapter(private val productRepository: ProductRepository) : GetProductsPort {
    override suspend fun getProducts(): List<Product> = productRepository.findAll().map(::toDomain)
}