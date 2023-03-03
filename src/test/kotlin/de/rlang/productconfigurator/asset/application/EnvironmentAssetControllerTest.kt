package de.rlang.productconfigurator.asset.application

import arrow.core.Either
import de.rlang.productconfigurator.asset.application.request.CreateEnvironmentRequest
import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.model.AssetType
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.inbound.CreateEnvironmentUseCase
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetAllEnvironmentsUseCase
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetEnvironmentUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.net.URI

class EnvironmentAssetControllerTest {

    val getAllEnvironmentsUseCase = mockk<GetAllEnvironmentsUseCase>()
    val getEnvironmentUseCase = mockk<GetEnvironmentUseCase>()
    val createEnvironmentUseCase = mockk<CreateEnvironmentUseCase>()
    val environmentAssetController =
        EnvironmentAssetController(createEnvironmentUseCase, getAllEnvironmentsUseCase, getEnvironmentUseCase)

    @Test
    fun `returns a 201 when environment has been successfully uploaded and created`() {
        val webTestClient = WebTestClient.bindToController(environmentAssetController).build()

        every { createEnvironmentUseCase.createEnvironment("Studio Environment", 100, 150, 1) } returns
                Either.Right(
                    Environment(
                        id = 3,
                        name = "Studio Environment",
                        radius = 100,
                        height = 150,
                        asset = Asset(
                            1,
                            "Studio Environment",
                            URI.create("http://localhost:8081/assets/studio_env.hdr"),
                            AssetType.Environment
                        )
                    )
                )

        val createEnvironmentRequest = CreateEnvironmentRequest(
            name = "Studio Environment",
            radius = 100,
            height = 150,
            assetId = 1
        )

        webTestClient.post().uri("/v1/assets/environments")
            .body(Mono.just(createEnvironmentRequest), createEnvironmentRequest::class.java)
            .exchange()
            .expectAll({
                it.expectStatus().isCreated
                it.expectHeader().location("/v1/assets/environments/3")
            })

        verify { createEnvironmentUseCase.createEnvironment("Studio Environment", 100, 150, 1) }
    }

    @Test
    fun `returns a list with only the default environment if no other envs are present`() {

        val webTestClient = WebTestClient.bindToController(environmentAssetController).build()
        every { getAllEnvironmentsUseCase.allEnvironments() } returns listOf(
            Environment(
                1, "Studio", 100, 150, Asset(
                    1,
                    "Studio Environment",
                    URI.create("http://localhost:8081/assets/studio_env.hdr"),
                    AssetType.Environment
                )
            )
        )

        webTestClient.get().uri("/v1/assets/environments").exchange().expectAll({
            it.expectStatus().isOk
            it.expectBodyList(Environment::class.java).hasSize(1)
        })
    }
}