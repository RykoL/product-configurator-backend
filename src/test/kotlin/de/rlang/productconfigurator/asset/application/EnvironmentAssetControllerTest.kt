package de.rlang.productconfigurator.asset.application

import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.model.AssetType
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.inbound.UploadEnvironmentUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.net.URI

class EnvironmentAssetControllerTest {

    @Test
    fun `returns a 201 when environment has been successfully uploaded and created`() {
        val uploadEnvironmentUseCase = mockk<UploadEnvironmentUseCase>()
        val environmentAssetController = EnvironmentAssetController(uploadEnvironmentUseCase)
        val webTestClient = WebTestClient.bindToController(environmentAssetController).build()

        every { uploadEnvironmentUseCase.uploadEnvironment("Studio Environment", any()) } returns Environment(
            1, "Studio Environment", Asset(
                1,
                "Studio Environment",
                URI.create("http://localhost:8081/assets/studio_env.hdr"),
                AssetType.Environment
            )
        )

        val multipartBodyBuilder = MultipartBodyBuilder()
        multipartBodyBuilder.part("environment", emptyArray<Byte>()).filename("studio_env.hdr")
        multipartBodyBuilder.part("name", "Studio Environment")

        webTestClient.post().uri("/v1/assets/environments")
            .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
            .exchange()
            .expectAll({
                it.expectStatus().isCreated
            })

        verify { uploadEnvironmentUseCase.uploadEnvironment("Studio Environment", any()) }
    }
}