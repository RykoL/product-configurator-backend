package de.rlang.productconfigurator.scene.domain.mapper

import de.rlang.productconfigurator.scene.domain.model.Environment
import de.rlang.productconfigurator.scene.domain.model.Scene
import de.rlang.productconfigurator.scene.domain.entity.Scene as SceneDAO

fun fromSceneDAO(sceneDao: SceneDAO): Scene =
    Scene(
        sceneDao.id!!,
        sceneDao.name,
        mutableListOf(),
        toEnvironmentModel(sceneDao.environment)
    )

fun toSceneEntity(scene: Scene): SceneDAO = SceneDAO(scene.id, scene.name, toEnvironmentEntity(scene.environment))