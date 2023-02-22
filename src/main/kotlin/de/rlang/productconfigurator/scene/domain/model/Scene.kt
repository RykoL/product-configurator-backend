package de.rlang.productconfigurator.scene.domain.model

data class Scene(
    val id: Long,
    val name: String,
    val products: MutableList<Product>,
    var environment: Environment
    ) {
    fun changeEnvironment(newEnvironment: Environment) {
        environment = newEnvironment
    }
}