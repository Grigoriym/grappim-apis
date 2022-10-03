package com.grappim

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.grappim.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt(), host = "0.0.0.0") {
        configureMonitoring()
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
