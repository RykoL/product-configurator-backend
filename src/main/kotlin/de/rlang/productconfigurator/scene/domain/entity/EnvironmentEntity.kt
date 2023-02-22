package de.rlang.productconfigurator.scene.domain.entity

import jakarta.persistence.*

@Entity(name = "environment")
class EnvironmentEntity(
    @Id
    @SequenceGenerator(name = "environment_id_seq", sequenceName = "environment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "environment_id_seq")
    var id: Long?,
    val name: String,
)