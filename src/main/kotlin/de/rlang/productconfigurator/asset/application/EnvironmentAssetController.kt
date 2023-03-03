package de.rlang.productconfigurator.asset.application

import de.rlang.productconfigurator.asset.application.request.CreateEnvironmentRequest
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.inbound.CreateEnvironmentUseCase
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetAllEnvironmentsUseCase
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetEnvironmentUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/v1/assets/environments")
class EnvironmentAssetController(
    val createEnvironmentUseCase: CreateEnvironmentUseCase,
    val getAllEnvironmentsUseCase: GetAllEnvironmentsUseCase,
    val getEnvironmentUseCase: GetEnvironmentUseCase
) {

    @PostMapping
    fun createEnvironment(@RequestBody createEnvironmentRequest: CreateEnvironmentRequest): ResponseEntity<Unit> =
        createEnvironmentUseCase.createEnvironment(
            createEnvironmentRequest.name,
            createEnvironmentRequest.radius,
            createEnvironmentRequest.height,
            createEnvironmentRequest.assetId
        ).fold(
            { ResponseEntity.notFound().build() },
            { ResponseEntity.created(URI.create("/v1/assets/environments/${it.id}")).build() }
        )

    @GetMapping("")
    fun allEnvironments() = getAllEnvironmentsUseCase.allEnvironments()

    @GetMapping("/{environmentId}")
    fun getEnvironment(@PathVariable environmentId: Long): ResponseEntity<Environment> =
        getEnvironmentUseCase.getEnvironment(environmentId).fold(
            { ResponseEntity.notFound().build() },
            { ResponseEntity.ok(it) }
        )
}