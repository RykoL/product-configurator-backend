package de.rlang.productconfigurator.scene.infrastructure

import de.rlang.productconfigurator.scene.domain.entity.Scene
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SceneRepository : CrudRepository<Scene, Long>