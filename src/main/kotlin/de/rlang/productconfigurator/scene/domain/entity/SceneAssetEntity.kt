package de.rlang.productconfigurator.scene.domain.entity

import de.rlang.productconfigurator.scene.domain.model.AssetType
import jakarta.persistence.*

@Entity
@Table(name = "asset")
class SceneAssetEntity(
    @Id
    @SequenceGenerator(name = "asset_id_seq", sequenceName = "asset_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_id_seq")
    val id: Long?,
    val name: String,
    val location: String,

    @Enumerated(EnumType.STRING)
    val assetType: AssetType
)