package de.rlang.productconfigurator.scene.domain.model

import java.net.URI

enum class AssetType {
    Environment
}

data class Asset(val id: Long, val name: String, val location: URI, val type: AssetType)