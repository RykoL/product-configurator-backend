package de.rlang.productconfigurator.asset.domain.entity

import de.rlang.productconfigurator.asset.domain.model.AssetType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity(name = "asset")
class AssetEntity(
    @Id
    @SequenceGenerator(name = "asset_id_seq", sequenceName = "asset_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_id_seq")
    val id: Long?,
    val name: String,
    val location: String,

    @Enumerated(EnumType.STRING)
    val assetType: AssetType
)