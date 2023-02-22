package de.rlang.productconfigurator.scene.application

import de.rlang.productconfigurator.scene.domain.model.Environment
import de.rlang.productconfigurator.scene.domain.ports.inbound.GetAllEnvironmentsUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin()
@RestController
@RequestMapping("/v1/environments")
class EnvironmentController(val getAllEnvironmentsUseCase: GetAllEnvironmentsUseCase) {

    @GetMapping("")
    fun allEnvironments() = getAllEnvironmentsUseCase.allEnvironments()
}