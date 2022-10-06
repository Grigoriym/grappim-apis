package com.grappim.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        statusPages()
    }
}

fun StatusPagesConfig.statusPages() {
    exception<AuthenticationException> { call, cause ->
        call.respond(HttpStatusCode.Unauthorized)
    }
    exception<AuthorizationException> { call, cause ->
        call.respond(HttpStatusCode.Forbidden)
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()