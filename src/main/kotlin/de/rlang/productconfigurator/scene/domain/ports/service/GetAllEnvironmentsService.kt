package de.rlang.productconfigurator.scene.domain.ports.service

import de.rlang.productconfigurator.scene.domain.model.Environment
import de.rlang.productconfigurator.scene.domain.ports.inbound.GetAllEnvironmentsUseCase
import de.rlang.productconfigurator.scene.domain.ports.outbound.EnvironmentPort
import org.springframework.stereotype.Service

@Service
class GetAllEnvironmentsService(val environmentPort: EnvironmentPort) : GetAllEnvironmentsUseCase {
    override fun allEnvironments(): List<Environment> {
        return environmentPort.getAllEnvironments()
    }
}