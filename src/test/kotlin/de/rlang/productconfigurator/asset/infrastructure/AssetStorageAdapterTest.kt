package de.rlang.productconfigurator.asset.infrastructure

import de.rlang.productconfigurator.asset.configuration.AssetStorageConfiguration
import de.rlang.productconfigurator.asset.domain.entity.AssetEntity
import de.rlang.productconfigurator.asset.domain.model.AssetType
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.HttpHeaders
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI
import java.nio.file.Path
import kotlin.io.path.Path

class AssetStorageAdapterTest {

    val file = object : FilePart {
        override fun name(): String = "Studio Environment"
        override fun headers(): HttpHeaders = HttpHeaders()
        override fun content(): Flux<DataBuffer> {
            TODO("Not yet implemented")
        }

        override fun filename(): String = "studio_env_4k.hdr"
        override fun transferTo(dest: Path): Mono<Void> = Mono.never()
    }

    @Test
    fun `properly constructs the location for the asset to be served`() {
        val assetRepository = mockk<AssetRepository>()
        val assetStorageConfiguration = AssetStorageConfiguration(
            "http://localhost:8081/assets",
            "./assets"
        )
        val assetStorageAdapter = AssetStorageAdapter(assetRepository, assetStorageConfiguration)

        assertEquals(
            URI.create("http://localhost:8081/assets/studio_env_4k.hdr"),
            assetStorageAdapter.constructLocation("studio_env_4k.hdr")
        )
    }

    @Test
    fun `calls the persistencePort to save the asset`() {
        val assetRepository = mockk<AssetRepository>()
        val assetStorageConfiguration = AssetStorageConfiguration(
            "http://localhost:8081/assets",
            "./assets"
        )
        //val assetStorageAdapter = AssetStorageAdapter(assetRepository, assetStorageConfiguration)
        val assetStorageAdapter = spyk(AssetStorageAdapter(assetRepository, assetStorageConfiguration))

        every { assetRepository.save(any()) } returns AssetEntity(1, "", "", AssetType.Environment)

        assetStorageAdapter.persistAsset("some-asset", file)

        verify { assetStorageAdapter.writeToDisk(file) }
    }


    @Test
    fun `saves the asset in the database`() {
        val assetRepository = mockk<AssetRepository>()
        val assetStorageConfiguration = AssetStorageConfiguration(
            "http://localhost:8081/assets",
            "./assets"
        )
        val assetStorageAdapter = AssetStorageAdapter(assetRepository, assetStorageConfiguration)

        val expectedEntity = AssetEntity(
            1,
            "studio_env_4k.hdr",
            "http://localhost:8081/assets/studio_env_4k.hdr",
            AssetType.Environment
        )

        every { assetRepository.save(any()) } returns expectedEntity

        assetStorageAdapter.persistAsset("some-asset", file)

        verify { assetRepository.save(any()) }
    }

    @Test
    fun `writes to the proper path in the filesystem`() {
        val assetRepository = mockk<AssetRepository>()
        val assetStorageConfiguration = AssetStorageConfiguration(
            "http://localhost:8081/assets",
            "/var/assets"
        )

        val assetStorageAdapter = AssetStorageAdapter(assetRepository, assetStorageConfiguration)
        val fileMock = mockk<FilePart>()

        every { fileMock.filename() } returns "studio_env_4k.hdr"
        every { fileMock.transferTo(Path("/var/assets/studio_env_4k.hdr")) } returns Mono.never()

        assetStorageAdapter.writeToDisk(fileMock)

        verify { fileMock.transferTo(Path("/var/assets/studio_env_4k.hdr")) }
    }
}