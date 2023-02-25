package de.rlang.productconfigurator.asset.domain.mapper

import de.rlang.productconfigurator.asset.domain.entity.AssetEntity
import de.rlang.productconfigurator.asset.domain.model.Asset
import java.net.URI

fun toAssetEntity(asset: Asset): AssetEntity =
    AssetEntity(asset.id, asset.name, asset.location.toASCIIString(), assetType = asset.assetType)

fun toAssetModel(asset: AssetEntity): Asset =
    Asset(
        asset.id!!,
        asset.name,
        URI.create(asset.location),
        asset.assetType
    )