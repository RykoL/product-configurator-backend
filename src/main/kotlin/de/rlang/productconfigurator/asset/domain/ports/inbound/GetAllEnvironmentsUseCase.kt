package de.rlang.productconfigurator.asset.domain.ports.inbound

import de.rlang.productconfigurator.asset.domain.model.Environment

interface GetAllEnvironmentsUseCase {
    fun allEnvironments(): List<Environment>
}