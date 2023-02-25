package de.rlang.productconfigurator.asset.domain.ports.inbound

import de.rlang.productconfigurator.asset.domain.model.Environment
import org.springframework.http.codec.multipart.FilePart

interface UploadEnvironmentUseCase {
    fun uploadEnvironment(name: String, file: FilePart): Environment
}