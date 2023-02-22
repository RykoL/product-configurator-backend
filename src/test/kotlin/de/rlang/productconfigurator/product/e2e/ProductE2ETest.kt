package de.rlang.productconfigurator.product.e2e

import de.rlang.productconfigurator.product.domain.entity.ProductEntity
import de.rlang.productconfigurator.product.domain.model.Product
import de.rlang.productconfigurator.product.infrastructure.ProductRepository
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@Tag("Integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductE2ETest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    fun `returns a list of products`() {

        webTestClient.get().uri("/v1/products").exchange().expectAll({
            it.expectStatus().isOk
            it.expectHeader().contentType(MediaType.APPLICATION_JSON)
            it.expectBodyList(Product::class.java).hasSize(2)
        })
    }
}