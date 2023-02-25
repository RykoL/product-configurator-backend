package de.rlang.productconfigurator.asset.domain.service

import de.rlang.productconfigurator.asset.domain.model.Asset
import de.rlang.productconfigurator.asset.domain.model.AssetType
import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.outbound.AssetStoragePort
import de.rlang.productconfigurator.asset.domain.ports.outbound.CreateEnvironmentPort
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.HttpHeaders
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI
import java.nio.file.Path

class UploadEnvironmentServiceTest {

    @Test
    fun `persists an asset and links it to an environment when uploading`() {
        val assetStoragePort = mockk<AssetStoragePort>()
        val createEnvironmentPort = mockk<CreateEnvironmentPort>()
        val uploadEnvironmentService = UploadEnvironmentService(assetStoragePort, createEnvironmentPort)

        val file = object : FilePart {
            override fun name(): String = "Studio Environment"
            override fun headers(): HttpHeaders = HttpHeaders()
            override fun content(): Flux<DataBuffer> {
                TODO("Not yet implemented")
            }

            override fun filename(): String = "studio_env_4k.hdr"
            override fun transferTo(dest: Path): Mono<Void> = Mono.never()
        }

        every { assetStoragePort.persistAsset("Studio Environment", file) } returns Asset(
            1,
            "studio_env_4k.hdr",
            URI.create("http://localhost:8081/assets/studio_env_4k.hdr"),
            AssetType.Environment
        )

        every {
            createEnvironmentPort.createEnvironment(
                "Studio Environment",
                Asset(
                    1, "studio_env_4k.hdr", URI.create("http://localhost:8081/assets/studio_env_4k.hdr"),
                    AssetType.Environment
                )
            )
        } returns Environment(
            1, "Studio Environment", Asset(
                1,
                "studio_env_4k.hdr",
                URI.create("http://localhost:8081/assets/studio_env_4k.hdr"),
                AssetType.Environment
            )
        )


        val environment = uploadEnvironmentService.uploadEnvironment("Studio Environment", file)

        val expectedEnv = Environment(
            1, "Studio Environment", Asset(
                1,
                "studio_env_4k.hdr",
                URI.create("http://localhost:8081/assets/studio_env_4k.hdr"),
                AssetType.Environment
            )
        )

        assertEquals(expectedEnv, environment)
    }
}