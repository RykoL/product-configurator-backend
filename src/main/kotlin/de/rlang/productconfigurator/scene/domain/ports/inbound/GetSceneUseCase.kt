package de.rlang.productconfigurator.scene.domain.ports.inbound

import de.rlang.productconfigurator.scene.domain.model.Scene

interface GetSceneUseCase {
    fun getScene(sceneId: Long): Scene
}