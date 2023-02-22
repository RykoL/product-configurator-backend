package de.rlang.productconfigurator.product.infrastructure

import de.rlang.productconfigurator.product.domain.entity.ProductEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<ProductEntity, Long> {
    override fun findAll(): List<ProductEntity>
}