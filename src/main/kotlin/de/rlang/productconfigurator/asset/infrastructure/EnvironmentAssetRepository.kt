package de.rlang.productconfigurator.asset.infrastructure

import de.rlang.productconfigurator.asset.domain.entity.EnvironmentEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EnvironmentAssetRepository : CrudRepository<EnvironmentEntity, Long> {
}