package de.rlang.productconfigurator.scene.domain.mapper

import de.rlang.productconfigurator.scene.domain.entity.EnvironmentEntity
import de.rlang.productconfigurator.scene.domain.model.Environment

fun toEnvironmentModel(env: EnvironmentEntity) = Environment(env.id!!, env.name, toAssetModel(env.asset))
fun toEnvironmentEntity(env: Environment) = EnvironmentEntity(env.id, env.name, toAssetEntity(env.asset))
