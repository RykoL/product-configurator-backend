package de.rlang.productconfigurator.asset.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "assets.storage")
data class AssetStorageConfiguration(
    var serverURL: String = "",
    var storagePath: String = ""
)