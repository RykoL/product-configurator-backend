package de.rlang.productconfigurator.product.application

import de.rlang.productconfigurator.product.domain.ports.inbound.GetProductsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/products")
class ProductController(private val getProductsUseCase: GetProductsUseCase) {

    @GetMapping("")
    suspend fun getAllProducts() = getProductsUseCase.getProducts()
}