package de.rlang.productconfigurator.scene.domain.mapper

import de.rlang.productconfigurator.scene.domain.entity.SceneAssetEntity
import de.rlang.productconfigurator.scene.domain.model.Asset
import java.net.URI

fun toAssetEntity(asset: Asset): SceneAssetEntity =
    SceneAssetEntity(asset.id, asset.name, asset.location.toASCIIString(), asset.type)

fun toAssetModel(asset: SceneAssetEntity): Asset =
    Asset(
        asset.id!!,
        asset.name,
        URI.create(asset.location),
        asset.assetType
    )