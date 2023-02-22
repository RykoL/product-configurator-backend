package de.rlang.productconfigurator.scene.domain.ports.outbound

import de.rlang.productconfigurator.scene.domain.model.Scene

interface ScenePort {
    fun saveNewScene(name: String): Scene
    fun getScene(sceneId: Long): Scene
    fun saveScene(scene: Scene): Scene
}