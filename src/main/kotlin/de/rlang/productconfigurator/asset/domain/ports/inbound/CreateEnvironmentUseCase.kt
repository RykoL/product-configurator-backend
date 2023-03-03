package de.rlang.productconfigurator.asset.domain.ports.inbound

import arrow.core.Either
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.error.DomainError

interface CreateEnvironmentUseCase {
    fun createEnvironment(name: String, radius: Int, height: Int, assetId: Long): Either<DomainError, Environment>
}
