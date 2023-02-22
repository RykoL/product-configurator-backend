package de.rlang.productconfigurator.product.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name="product")
class ProductEntity(
    @Id
    val id: Long,
    val name: String
)