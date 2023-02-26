package de.rlang.productconfigurator.asset.domain.ports.outbound

import arrow.core.Either
import de.rlang.productconfigurator.asset.domain.model.Environment

interface EnvironmentPort {
    fun defaultEnvironment(): Environment
    fun getAllEnvironments(): List<Environment>
    fun getEnvironment(environmentId: Long): Either<Unit, Environment>
}