package de.rlang.productconfigurator.scene.domain.model

enum class AssetType {
    GLTF,
    HDRI
}

data class Asset(val id: String, val path: String, val type: AssetType)