package de.rlang.productconfigurator.scene.domain.ports.service

import de.rlang.productconfigurator.scene.application.request.CreateSceneRequest
import de.rlang.productconfigurator.scene.domain.model.Scene
import de.rlang.productconfigurator.scene.domain.ports.inbound.CreateSceneUseCase
import de.rlang.productconfigurator.scene.domain.ports.outbound.ScenePort
import org.springframework.stereotype.Service

@Service
class CreateSceneService(val scenePort: ScenePort) :
    CreateSceneUseCase {
    override fun createScene(createSceneRequest: CreateSceneRequest): Scene {
        return scenePort.saveNewScene(createSceneRequest.name)
    }
}