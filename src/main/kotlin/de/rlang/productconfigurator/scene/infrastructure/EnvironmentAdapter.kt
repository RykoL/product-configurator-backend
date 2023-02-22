package de.rlang.productconfigurator.scene.infrastructure

import arrow.core.Either
import de.rlang.productconfigurator.scene.domain.error.DomainError
import de.rlang.productconfigurator.scene.domain.mapper.toEnvironmentModel
import de.rlang.productconfigurator.scene.domain.model.Environment
import de.rlang.productconfigurator.scene.domain.ports.outbound.EnvironmentPort
import de.rlang.productconfigurator.unwrap
import org.springframework.stereotype.Component

@Component
class EnvironmentAdapter(val environmentRepository: EnvironmentRepository) : EnvironmentPort {
    override fun defaultEnvironment(): Environment = Environment(0, "Studio")

    override fun getAllEnvironments(): List<Environment> =
        environmentRepository.findAll().map(::toEnvironmentModel)

    override fun getEnvironment(environmentId: Long): Either<DomainError, Environment> =
        Either.fromNullable(environmentRepository.findById(environmentId).unwrap())
            .map(::toEnvironmentModel)
            .mapLeft { DomainError.EnvironmentNotFound }
}