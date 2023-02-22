package de.rlang.productconfigurator.product.application

import de.rlang.productconfigurator.product.domain.model.Product
import de.rlang.productconfigurator.product.domain.ports.inbound.GetProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

class ProductControllerTest {

    private val getProductsUseCase = mockk<GetProductsUseCase>()
    private val webTestClient = WebTestClient.bindToController(ProductController(getProductsUseCase)).build()

    @Test
    fun `returns list of products`() {

        val products = listOf(
            Product(0, "teapot", emptyList()),
            Product(1, "avocado", emptyList())
        )

        coEvery { getProductsUseCase.getProducts() } returns products

        webTestClient.get()
            .uri("/v1/products")
            .exchange()
            .expectAll({
                it.expectStatus().isOk
                it.expectHeader().contentType(MediaType.APPLICATION_JSON)
                it.expectBodyList(Product::class.java)
            })
    }
}