package de.rlang.productconfigurator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductConfiguratorApplication

fun main(args: Array<String>) {
	runApplication<ProductConfiguratorApplication>(*args)
}
