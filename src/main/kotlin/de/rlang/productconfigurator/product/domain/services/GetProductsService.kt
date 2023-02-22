package de.rlang.productconfigurator.product.domain.services

import de.rlang.productconfigurator.product.domain.model.Product
import de.rlang.productconfigurator.product.domain.ports.inbound.GetProductsUseCase
import de.rlang.productconfigurator.product.domain.ports.outbound.GetProductsPort
import org.springframework.stereotype.Service

@Service
class GetProductsService(private val getProductsPort: GetProductsPort) : GetProductsUseCase {
    override suspend fun getProducts(): List<Product> = getProductsPort.getProducts()
}