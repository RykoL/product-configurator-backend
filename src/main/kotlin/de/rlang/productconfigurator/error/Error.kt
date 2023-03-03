package de.rlang.productconfigurator.error


sealed class DomainError() {
    object EnvironmentNotFound : DomainError()
    object AssetNotFound : DomainError()
}
