package de.rlang.productconfigurator.asset.domain.entity

import jakarta.persistence.*

@Entity(name = "EnvironmentAsset")
@Table(name = "environment")
class EnvironmentEntity(
    @Id
    @SequenceGenerator(name = "environment_id_seq", sequenceName = "environment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "environment_id_seq")
    var id: Long?,
    val name: String,
    val radius: Int,
    val height: Int,
    @OneToOne
    val asset: AssetEntity
)