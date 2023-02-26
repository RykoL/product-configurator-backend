package de.rlang.productconfigurator.asset.domain.service

import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetAllEnvironmentsUseCase
import de.rlang.productconfigurator.asset.domain.ports.outbound.EnvironmentPort
import org.springframework.stereotype.Service

@Service
class GetAllEnvironmentsService(val environmentPort: EnvironmentPort) : GetAllEnvironmentsUseCase {
    override fun allEnvironments(): List<Environment> {
        return environmentPort.getAllEnvironments()
    }
}