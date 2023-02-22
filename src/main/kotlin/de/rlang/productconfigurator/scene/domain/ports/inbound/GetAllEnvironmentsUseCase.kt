package de.rlang.productconfigurator.scene.domain.ports.inbound

import de.rlang.productconfigurator.scene.domain.model.Environment

interface GetAllEnvironmentsUseCase {
    fun allEnvironments(): List<Environment>
}