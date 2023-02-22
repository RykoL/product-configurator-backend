package de.rlang.productconfigurator.product.infrastructure

import de.rlang.productconfigurator.product.domain.entity.ProductEntity
import de.rlang.productconfigurator.product.domain.model.Product
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetProductsAdapterTest {


    @Test
    suspend fun `returns a list of product entities`() {
        val productRepository = mockk<ProductRepository>()

        every { productRepository.findAll() } returns listOf(
            ProductEntity(0, "avocado"),
            ProductEntity(1, "teapot")
        )

        val getProductsAdapter = GetProductsAdapter(productRepository)

        assertEquals(getProductsAdapter.getProducts(), listOf(
            Product(0, "avocado", emptyList()),
            Product(1, "teaprot", emptyList()),
        ))
    }
}