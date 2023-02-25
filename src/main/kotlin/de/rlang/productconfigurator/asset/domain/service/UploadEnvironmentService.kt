package de.rlang.productconfigurator.asset.domain.service

import de.rlang.productconfigurator.asset.domain.model.Environment
import de.rlang.productconfigurator.asset.domain.ports.inbound.UploadEnvironmentUseCase
import de.rlang.productconfigurator.asset.domain.ports.outbound.AssetStoragePort
import de.rlang.productconfigurator.asset.domain.ports.outbound.CreateEnvironmentPort
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service

@Service
class UploadEnvironmentService(val assetStoragePort: AssetStoragePort, createEnvironmentPort: CreateEnvironmentPort) : UploadEnvironmentUseCase {
    override fun uploadEnvironment(name: String, file: FilePart): Environment {
        val asset = assetStoragePort.persistAsset(name, file)
        return Environment(1, "Studio Environment", asset)
    }
}