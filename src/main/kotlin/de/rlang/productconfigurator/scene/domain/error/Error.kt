package de.rlang.productconfigurator.scene.domain.error


sealed class DomainError() {
    object EnvironmentNotFound : DomainError()
}
