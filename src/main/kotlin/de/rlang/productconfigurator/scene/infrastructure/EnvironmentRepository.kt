package de.rlang.productconfigurator.scene.infrastructure

import de.rlang.productconfigurator.scene.domain.entity.EnvironmentEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EnvironmentRepository : CrudRepository<EnvironmentEntity, Long>