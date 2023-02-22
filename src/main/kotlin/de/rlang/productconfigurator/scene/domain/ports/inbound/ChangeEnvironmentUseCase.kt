package de.rlang.productconfigurator.scene.domain.ports.inbound

import arrow.core.Either
import de.rlang.productconfigurator.scene.domain.error.DomainError
import de.rlang.productconfigurator.scene.domain.model.Scene

interface ChangeEnvironmentUseCase {
    fun changeEnvironment(sceneId: Long, environmentId: Long): Either<DomainError, Scene>
}
