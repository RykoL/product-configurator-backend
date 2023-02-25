package de.rlang.productconfigurator.asset.infrastructure

import de.rlang.productconfigurator.asset.domain.entity.EnvironmentEntity
import de.rlang.productconfigurator.asset.domain.mapper.toAssetEntity
import de.rlang.productconfigurator.asset.domain.mapper.toEnvironmentModel
import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.outbound.CreateEnvironmentPort
import org.springframework.stereotype.Component

@Component
class CreateEnvironmentAdapter(val environmentRepository: EnvironmentAssetRepository) : CreateEnvironmentPort {
    override fun createEnvironment(name: String, asset: Asset): Environment {
        var environmentEntity = EnvironmentEntity(
            id = null,
            name = name,
            asset = toAssetEntity(asset)
        )

        environmentEntity = environmentRepository.save(environmentEntity)

        return toEnvironmentModel(environmentEntity)
    }
}