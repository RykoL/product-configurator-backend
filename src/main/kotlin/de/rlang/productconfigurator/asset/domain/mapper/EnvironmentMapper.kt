package de.rlang.productconfigurator.asset.domain.mapper

import de.rlang.productconfigurator.asset.domain.entity.EnvironmentEntity
import de.rlang.productconfigurator.asset.domain.model.Environment

fun toEnvironmentModel(environmentEntity: EnvironmentEntity): Environment = Environment(
    environmentEntity.id!!,
    environmentEntity.name,
    environmentEntity.radius,
    environmentEntity.height,
    toAssetModel(environmentEntity.asset)
)