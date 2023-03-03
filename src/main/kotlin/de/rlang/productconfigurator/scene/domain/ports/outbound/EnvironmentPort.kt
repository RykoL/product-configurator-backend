package de.rlang.productconfigurator.scene.domain.ports.outbound

import arrow.core.Either
import de.rlang.productconfigurator.error.DomainError
import de.rlang.productconfigurator.scene.domain.model.Environment

interface EnvironmentPort {
    fun defaultEnvironment(): Environment
    fun getAllEnvironments(): List<Environment>
    fun getEnvironment(environmentId: Long): Either<DomainError, Environment>
}