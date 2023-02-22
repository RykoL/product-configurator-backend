package de.rlang.productconfigurator.scene.domain.ports.inbound

import de.rlang.productconfigurator.scene.application.request.CreateSceneRequest
import de.rlang.productconfigurator.scene.domain.model.Scene

interface CreateSceneUseCase {
    fun createScene(createSceneRequest: CreateSceneRequest): Scene
}