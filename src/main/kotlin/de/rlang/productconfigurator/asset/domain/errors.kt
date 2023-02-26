package de.rlang.productconfigurator.asset.domain


sealed class InfrastructureError() {
    data class AssetPersistenceFailure(val message: String) : InfrastructureError()
}