package de.rlang.productconfigurator.asset.infrastructure

import de.rlang.productconfigurator.asset.domain.entity.AssetEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AssetRepository : CrudRepository<AssetEntity, Long> {
}