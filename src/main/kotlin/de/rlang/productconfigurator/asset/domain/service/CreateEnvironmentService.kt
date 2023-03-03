package de.rlang.productconfigurator.asset.domain.service

import arrow.core.Either
import arrow.core.rightIfNotNull
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.inbound.CreateEnvironmentUseCase
import de.rlang.productconfigurator.asset.domain.ports.outbound.AssetPort
import de.rlang.productconfigurator.asset.domain.ports.outbound.CreateEnvironmentPort
import de.rlang.productconfigurator.error.DomainError
import org.springframework.stereotype.Service

@Service
class CreateEnvironmentService(
    val createEnvironmentPort: CreateEnvironmentPort,
    val assetPort: AssetPort
) : CreateEnvironmentUseCase {
    override fun createEnvironment(
        name: String,
        radius: Int,
        height: Int,
        assetId: Long
    ): Either<DomainError, Environment> =
        assetPort.getAsset(assetId)
            .rightIfNotNull { DomainError.AssetNotFound }
            .map { createEnvironmentPort.createEnvironment(name, radius, height, it) }

}