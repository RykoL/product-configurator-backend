package de.rlang.productconfigurator.scene.e2e

import de.rlang.productconfigurator.scene.application.request.CreateSceneRequest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.net.http.HttpHeaders

@Tag("Integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SceneE2ETest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun `creates a new scene`() {

        val createSceneRequest = CreateSceneRequest(name = "Scene With Teapot")


        webTestClient.post().uri("/v1/scenes")
            .body(Mono.just(createSceneRequest), CreateSceneRequest::class.java)
            .exchange()
            .expectAll({
                it.expectStatus().isCreated
            })
    }

    @Test
    fun `allows for cross origin requests`() {

        val createSceneRequest = CreateSceneRequest(name = "Scene With Teapot")

        webTestClient.post().uri("/v1/scenes")
            .headers {
                it.contentType = MediaType.APPLICATION_JSON
                it.origin = "http://localhost:8080"
            }
            .body(Mono.just(createSceneRequest), CreateSceneRequest::class.java)
            .exchange()
            .expectAll({
                it.expectHeader().exists("Access-Control-Allow-Origin")
            })
    }
}