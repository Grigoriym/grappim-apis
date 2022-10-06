package com.grappim

import com.grappim.plugins.configureStatusPages
import com.grappim.plugins.configureMonitoring
import com.grappim.plugins.configureRouting
import com.grappim.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt(), host = "0.0.0.0") {
        configureMonitoring()
        configureSerialization()
        configureStatusPages()
        configureRouting()
    }.start(wait = true)
}
