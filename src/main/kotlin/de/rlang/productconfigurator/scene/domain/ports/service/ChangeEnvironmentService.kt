package de.rlang.productconfigurator.scene.domain.ports.service

import arrow.core.Either
import de.rlang.productconfigurator.scene.domain.error.DomainError
import de.rlang.productconfigurator.scene.domain.model.Scene
import de.rlang.productconfigurator.scene.domain.ports.inbound.ChangeEnvironmentUseCase
import de.rlang.productconfigurator.scene.domain.ports.outbound.EnvironmentPort
import de.rlang.productconfigurator.scene.domain.ports.outbound.ScenePort
import org.springframework.stereotype.Service

@Service
class ChangeEnvironmentService(val scenePort: ScenePort, val environmentPort: EnvironmentPort) :
    ChangeEnvironmentUseCase {

    override fun changeEnvironment(sceneId: Long, environmentId: Long): Either<DomainError, Scene> {

        val scene = scenePort.getScene(sceneId)
        return environmentPort.getEnvironment(environmentId)
            .map {
                scene.changeEnvironment(it)
                it
            }.map { scenePort.saveScene(scene) }
    }
}