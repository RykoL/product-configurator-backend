package de.rlang.productconfigurator.asset.e2e

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import kotlin.io.path.Path
import kotlin.io.path.exists

@Tag("Integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UploadAssetE2ETest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun `uploads environment asset`() {

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

        assertEquals(true, Path("./assets", "studio_env.hdr").exists())
    }
}