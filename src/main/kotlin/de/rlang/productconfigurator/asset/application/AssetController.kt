package de.rlang.productconfigurator.asset.application

import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.ports.inbound.UploadAssetUseCase
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/v1/assets")
class AssetController(val uploadEnvironmentUseCase: UploadAssetUseCase) {


    @PostMapping("", produces = ["application/json"])
    fun uploadAsset(
        @RequestPart("name") name: String,
        @RequestPart("type") assetType: String,
        @RequestPart("environment") environment: FilePart
    ): ResponseEntity<Asset> {
        val asset = uploadEnvironmentUseCase.uploadAsset(name, environment)
        return ResponseEntity.created(URI.create("/v1/assets/${asset.id}")).body(asset)
    }
}