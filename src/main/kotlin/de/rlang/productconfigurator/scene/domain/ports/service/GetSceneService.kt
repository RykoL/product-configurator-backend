package de.rlang.productconfigurator.scene.domain.ports.service

import de.rlang.productconfigurator.scene.domain.model.Scene
import de.rlang.productconfigurator.scene.domain.ports.inbound.GetSceneUseCase
import de.rlang.productconfigurator.scene.domain.ports.outbound.ScenePort
import org.springframework.stereotype.Service

@Service
class GetSceneService(val scenePort: ScenePort) : GetSceneUseCase{
    override fun getScene(sceneId: Long): Scene {
        return scenePort.getScene(sceneId)
    }
}