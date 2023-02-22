package de.rlang.productconfigurator.scene.infrastructure

import de.rlang.productconfigurator.scene.domain.entity.Scene as SceneDAO
import de.rlang.productconfigurator.scene.domain.mapper.fromSceneDAO
import de.rlang.productconfigurator.scene.domain.mapper.toSceneEntity
import de.rlang.productconfigurator.scene.domain.model.Scene
import de.rlang.productconfigurator.scene.domain.ports.outbound.ScenePort
import org.springframework.stereotype.Component

@Component
class SceneAdapter(val sceneRepository: SceneRepository, val environmentRepository: EnvironmentRepository) :
    ScenePort {

    override fun saveNewScene(name: String): Scene {

        val defaultEnv = environmentRepository.findById(1).get()
        val sceneEntity = SceneDAO(id = null, name = name, environment = defaultEnv)
        sceneRepository.save(sceneEntity)

        return fromSceneDAO(sceneEntity)
    }

    override fun getScene(sceneId: Long): Scene {
        val scene = sceneRepository.findById(sceneId).get()
        return fromSceneDAO(scene)
    }

    override fun saveScene(scene: Scene): Scene {
        val sceneEntity = toSceneEntity(scene)

        return fromSceneDAO(sceneRepository.save(sceneEntity))
    }
}