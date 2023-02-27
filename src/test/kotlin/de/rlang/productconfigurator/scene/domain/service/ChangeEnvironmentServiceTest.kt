package de.rlang.productconfigurator.scene.domain.service

import arrow.core.Either
import de.rlang.productconfigurator.scene.domain.model.Asset
import de.rlang.productconfigurator.scene.domain.model.AssetType
import de.rlang.productconfigurator.scene.domain.model.Environment
import de.rlang.productconfigurator.scene.domain.model.Scene
import de.rlang.productconfigurator.scene.domain.ports.outbound.EnvironmentPort
import de.rlang.productconfigurator.scene.domain.ports.outbound.ScenePort
import de.rlang.productconfigurator.scene.domain.ports.service.ChangeEnvironmentService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.net.URI

class ChangeEnvironmentServiceTest {


    @Test
    fun `updates the environment in a scene and persists it`() {
        val scenePort = mockk<ScenePort>()
        val environmentPort = mockk<EnvironmentPort>()
        val changeEnvironmentService = ChangeEnvironmentService(scenePort, environmentPort)

        every { scenePort.getScene(2) } returns Scene(
            2,
            "New Scene",
            mutableListOf(),
            environment = Environment(1, "Studio", Asset(1, "Studio", URI.create(""), AssetType.Environment))
        )

        every { environmentPort.getEnvironment(3) } returns Either.Right(
            Environment(
                3,
                "Beach",
                Asset(1, "Studio", URI.create(""), AssetType.Environment)
            )
        )


        val expectedScene = Scene(
            2,
            "New Scene",
            mutableListOf(),
            environment = Environment(3, "Beach", Asset(1, "Studio", URI.create(""), AssetType.Environment))
        )
        every { scenePort.saveScene(any()) } returns expectedScene

        val scene = changeEnvironmentService.changeEnvironment(2, 3)

        verify { scenePort.saveScene(expectedScene) }
        assertEquals(Either.Right(expectedScene), scene)
    }
}