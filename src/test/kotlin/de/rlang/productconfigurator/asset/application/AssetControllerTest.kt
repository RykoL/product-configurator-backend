package de.rlang.productconfigurator.asset.application

import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.model.AssetType
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetAllEnvironmentsUseCase
import de.rlang.productconfigurator.asset.domain.ports.inbound.GetEnvironmentUseCase
import de.rlang.productconfigurator.asset.domain.ports.inbound.UploadAssetUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.net.URI

class AssetControllerTest {

    val uploadAssetUseCase = mockk<UploadAssetUseCase>()
    val assetController =
        AssetController(uploadAssetUseCase)

    @Test
    fun `returns a 201 when asset has been successfully uploaded`() {
        val webTestClient = WebTestClient.bindToController(assetController).build()

        every { uploadAssetUseCase.uploadAsset("Studio Environment", any()) } returns Asset(
            1,
            "Studio Environment",
            URI.create("http://localhost:8081/assets/studio_env.hdr"),
            AssetType.Environment
        )
        val multipartBodyBuilder = MultipartBodyBuilder()
        multipartBodyBuilder.part("environment", emptyArray<Byte>()).filename("studio_env.hdr")
        multipartBodyBuilder.part("name", "Studio Environment")
        multipartBodyBuilder.part("type", "Environment")

        webTestClient.post().uri("/v1/assets")
            .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
            .exchange()
            .expectAll({
                it.expectStatus().isCreated
            })

        verify { uploadAssetUseCase.uploadAsset("Studio Environment", any()) }
    }
}