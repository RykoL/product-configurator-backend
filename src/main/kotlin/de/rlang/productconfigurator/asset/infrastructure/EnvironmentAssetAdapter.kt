package de.rlang.productconfigurator.asset.infrastructure

import arrow.core.Either
import de.rlang.productconfigurator.asset.domain.mapper.toEnvironmentModel
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.outbound.EnvironmentPort
import de.rlang.productconfigurator.unwrap
import org.springframework.stereotype.Component

@Component
class EnvironmentAssetAdapter(val environmentRepository: EnvironmentAssetRepository) : EnvironmentPort {
    override fun defaultEnvironment(): Environment = environmentRepository.findById(1)
        .map(::toEnvironmentModel)
        .get()

    override fun getAllEnvironments(): List<Environment> =
        environmentRepository.findAll().map(::toEnvironmentModel)

    override fun getEnvironment(environmentId: Long): Either<Unit, Environment> =
        Either.fromNullable(environmentRepository.findById(environmentId).unwrap())
            .map(::toEnvironmentModel)
            .mapLeft { Unit }
}