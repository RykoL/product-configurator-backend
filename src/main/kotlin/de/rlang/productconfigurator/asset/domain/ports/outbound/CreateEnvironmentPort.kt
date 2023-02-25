package de.rlang.productconfigurator.asset.domain.ports.outbound

import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.model.Environment

interface CreateEnvironmentPort {
    fun createEnvironment(name: String, asset: Asset): Environment
}