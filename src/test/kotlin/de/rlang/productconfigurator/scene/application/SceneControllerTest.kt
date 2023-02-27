package de.rlang.productconfigurator.scene.application

import arrow.core.Either
import de.rlang.productconfigurator.scene.application.request.ChangeEnvironmentRequest
import de.rlang.productconfigurator.scene.application.request.CreateSceneRequest
import de.rlang.productconfigurator.scene.domain.model.Asset
import de.rlang.productconfigurator.scene.domain.model.AssetType
import de.rlang.productconfigurator.scene.domain.model.Environment
import de.rlang.productconfigurator.scene.domain.model.Scene
import de.rlang.productconfigurator.scene.domain.ports.inbound.ChangeEnvironmentUseCase
import de.rlang.productconfigurator.scene.domain.ports.inbound.CreateSceneUseCase
import de.rlang.productconfigurator.scene.domain.ports.inbound.GetSceneUseCase
import de.rlang.productconfigurator.scene.infrastructure.SceneRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.net.URI

class SceneControllerTest {

    val createSceneUseCase = mockk<CreateSceneUseCase>()
    val getSceneUseCase = mockk<GetSceneUseCase>()
    val changeEnvironmentUseCase = mockk<ChangeEnvironmentUseCase>()
    val sceneRepository = mockk<SceneRepository>()
    val webTestClient = WebTestClient.bindToController(
        SceneController(
            createSceneUseCase,
            getSceneUseCase,
            changeEnvironmentUseCase,
            sceneRepository
        )
    ).build()

    @Test
    fun `returns the scene upon creation`() {
        val createSceneRequest = CreateSceneRequest("New Scene 1")

        every { createSceneUseCase.createScene(createSceneRequest) } returns Scene(
            1,
            "New Scene 1",
            mutableListOf(),
            Environment(1, "Studio", Asset(1, "Studio", URI.create(""), AssetType.Environment))
        )

        webTestClient.post().uri("/v1/scenes")
            .body(Mono.just(createSceneRequest), CreateSceneRequest::class.java)
            .exchange()
            .expectAll({
                it.expectStatus().isCreated
                it.expectBody()
                    .jsonPath("$.id").isEqualTo(1)
                    .jsonPath("$.name").isEqualTo("New Scene 1")
            })
    }

    @Test
    fun `returns the scene when scene was found`() {
        val sceneId = 1L

        every { getSceneUseCase.getScene(sceneId) } returns Scene(
            1,
            "New Scene 1",
            mutableListOf(),
            Environment(1, "Studio", Asset(1, "Studio", URI.create(""), AssetType.Environment))
        )

        webTestClient.get().uri("/v1/scenes/$sceneId")
            .exchange()
            .expectAll({
                it.expectStatus().isOk
                it.expectBody()
                    .jsonPath("$.id").isEqualTo(1)
                    .jsonPath("$.name").isEqualTo("New Scene 1")
                    .jsonPath("$.environment").exists()
                    .jsonPath("$.environment.id").isEqualTo(1)
                    .jsonPath("$.environment.name").isEqualTo("Studio")
            })
    }

    @Test
    fun `returns a 200 when environment has been successfully updated`() {
        val changeEnvironmentRequest = ChangeEnvironmentRequest(2)

        val sceneId = 1L

        every { changeEnvironmentUseCase.changeEnvironment(sceneId, changeEnvironmentRequest.environmentId) } returns
                Either.Right(
                    Scene(
                        1,
                        "New Scene 1",
                        mutableListOf(),
                        Environment(2, "Beach", Asset(2, "Beach", URI.create(""), AssetType.Environment))
                    )
                )

        webTestClient.put().uri("/v1/scenes/$sceneId/environment")
            .body(Mono.just(changeEnvironmentRequest), ChangeEnvironmentRequest::class.java)
            .exchange()
            .expectAll({
                it.expectStatus().isOk
                it.expectBody()
                    .jsonPath("$.id").isEqualTo(1)
                    .jsonPath("$.name").isEqualTo("New Scene 1")
                    .jsonPath("$.environment.id").isEqualTo(2)
                    .jsonPath("$.environment.name").isEqualTo("Beach")
            })
    }
}