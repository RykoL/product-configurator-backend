package de.rlang.productconfigurator.asset.domain.ports.inbound

import arrow.core.Either
import de.rlang.productconfigurator.asset.domain.model.Environment

interface GetEnvironmentUseCase {
    fun getEnvironment(id: Long): Either<Unit, Environment>
}