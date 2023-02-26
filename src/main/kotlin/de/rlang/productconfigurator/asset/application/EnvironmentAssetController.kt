package de.rlang.productconfigurator.asset.application

import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.inbound.UploadEnvironmentUseCase
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetAllEnvironmentsUseCase
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetEnvironmentUseCase
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/v1/assets/environments")
class EnvironmentAssetController(
    val uploadEnvironmentUseCase: UploadEnvironmentUseCase,
    val getAllEnvironmentsUseCase: GetAllEnvironmentsUseCase,
    val getEnvironmentUseCase: GetEnvironmentUseCase
) {

    @PostMapping("", produces = ["application/json"])
    fun uploadEnvironment(
        @RequestPart("name") name: String,
        @RequestPart("environment") environment: FilePart
    ): ResponseEntity<Unit> {
        val asset = uploadEnvironmentUseCase.uploadEnvironment(name, environment)
        return ResponseEntity.created(URI.create("/v1/assets/${asset.id}")).build()
    }

    @GetMapping("")
    fun allEnvironments() = getAllEnvironmentsUseCase.allEnvironments()

    @GetMapping("/{environmentId}")
    fun getEnvironment(@PathVariable environmentId: Long): ResponseEntity<Environment> =
        getEnvironmentUseCase.getEnvironment(environmentId).fold(
            {ResponseEntity.notFound().build()},
            {ResponseEntity.ok(it)}
        )
}