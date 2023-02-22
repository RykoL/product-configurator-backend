package de.rlang.productconfigurator.scene.application

import de.rlang.productconfigurator.scene.domain.model.Environment
import de.rlang.productconfigurator.scene.domain.ports.inbound.GetAllEnvironmentsUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient

class EnvironmentControllerTest {

    val getAllEnvironmentsUseCase = mockk<GetAllEnvironmentsUseCase>()
    val webTestClient = WebTestClient.bindToController(EnvironmentController(getAllEnvironmentsUseCase)).build()

    @Test
    fun `returns a list with only the default environment if no other envs are present`() {

        every { getAllEnvironmentsUseCase.allEnvironments() } returns listOf(
            Environment(1, "Studio")
        )

        webTestClient.get().uri("/v1/environments").exchange().expectAll({
            it.expectStatus().isOk
            it.expectBodyList(Environment::class.java).hasSize(1)
        })
    }
}