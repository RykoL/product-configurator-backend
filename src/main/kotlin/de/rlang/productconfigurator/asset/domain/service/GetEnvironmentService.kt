package de.rlang.productconfigurator.asset.domain.service

import arrow.core.Either
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetEnvironmentUseCase
import de.rlang.productconfigurator.asset.domain.ports.outbound.EnvironmentPort
import org.springframework.stereotype.Service

@Service
class GetEnvironmentService(val environmentPort: EnvironmentPort) : GetEnvironmentUseCase {
    override fun getEnvironment(id: Long): Either<Unit, Environment> {
        return environmentPort.getEnvironment(id)
    }
}