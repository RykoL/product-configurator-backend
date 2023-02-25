package de.rlang.productconfigurator.asset.application

import de.rlang.productconfigurator.asset.domain.ports.inbound.UploadEnvironmentUseCase
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.net.URI

@RestController
@RequestMapping("/v1/assets/environments")
class EnvironmentAssetController(val uploadEnvironmentUseCase: UploadEnvironmentUseCase) {

    @PostMapping("", produces = ["application/json"])
    fun uploadEnvironment(@RequestPart("name") name: String, @RequestPart("environment") environment: FilePart): ResponseEntity<Unit> {
        val asset = uploadEnvironmentUseCase.uploadEnvironment(name, environment)
        return ResponseEntity.created(URI.create("/v1/assets/${asset.id}")).build()
    }
}