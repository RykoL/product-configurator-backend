package de.rlang.productconfigurator.scene.infrastructure

import de.rlang.productconfigurator.scene.domain.entity.EnvironmentEntity
import org.springframework.data.repository.CrudRepository

interface EnvironmentRepository : CrudRepository<EnvironmentEntity, Long>