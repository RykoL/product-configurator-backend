package de.rlang.productconfigurator.scene.application

import arrow.core.Either
import de.rlang.productconfigurator.scene.application.request.ChangeEnvironmentRequest
import de.rlang.productconfigurator.scene.application.request.CreateSceneRequest
import de.rlang.productconfigurator.scene.domain.mapper.fromSceneDAO
import de.rlang.productconfigurator.scene.domain.model.Scene
import de.rlang.productconfigurator.scene.domain.ports.inbound.ChangeEnvironmentUseCase
import de.rlang.productconfigurator.scene.domain.ports.inbound.CreateSceneUseCase
import de.rlang.productconfigurator.scene.domain.ports.inbound.GetSceneUseCase
import de.rlang.productconfigurator.scene.infrastructure.SceneRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/v1/scenes")
class SceneController(
    val createSceneUseCase: CreateSceneUseCase,
    val getSceneUseCase: GetSceneUseCase,
    val changeEnvironmentUseCase: ChangeEnvironmentUseCase,
    val sceneRepository: SceneRepository
) {

    private val logger = LoggerFactory.getLogger(SceneController::class.java);

    @PostMapping("", consumes = ["application/json"])
    fun newScene(@RequestBody createSceneRequest: CreateSceneRequest): ResponseEntity<Scene> {
        logger.info("Creating new scene with name ${createSceneRequest.name}")
        val scene = createSceneUseCase.createScene(createSceneRequest)
        return ResponseEntity.created(URI.create("/v1/scenes/${scene.id}")).body(scene)
    }

    @GetMapping("/{sceneId}")
    fun getScene(@PathVariable sceneId: Long): ResponseEntity<Scene> =
        ResponseEntity.ok(getSceneUseCase.getScene(sceneId))

    @GetMapping("")
    fun getAllScenes(): ResponseEntity<List<Scene>> =
        ResponseEntity.ok(sceneRepository.findAll().map(::fromSceneDAO))

    @PutMapping("/{sceneId}/environment")
    fun changeEnvironment(
        @PathVariable sceneId: Long,
        @RequestBody changeEnvironmentRequest: ChangeEnvironmentRequest
    ): ResponseEntity<Scene> =
        changeEnvironmentUseCase
            .changeEnvironment(sceneId, changeEnvironmentRequest.environmentId).fold({
                ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            }, {
                ResponseEntity.ok(it)
            })
}