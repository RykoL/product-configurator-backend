package de.rlang.productconfigurator

import java.util.*

fun <T> Optional<T>.unwrap(): T? = orElse(null)

