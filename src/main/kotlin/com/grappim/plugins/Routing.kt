package com.grappim.plugins

import com.grappim.data.SignInDataDTO
import com.grappim.data.SignUpDataDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, cause ->
            call.respond(HttpStatusCode.Forbidden)
        }

    }

    routing {
        route("/api/v1") {
            post("/signin") {
                val body = call.receive<SignInDataDTO>()
                if (body.email.isEmpty() || body.password.isEmpty()) {
                    call.respond(
                        status = HttpStatusCode.Conflict,
                        message = mapOf(
                            "message" to "data must not be empty"
                        )
                    )
                } else {
                    call.respond(HttpStatusCode.OK)
                }
            }
            post("/signup") {
                val body = call.receive<SignUpDataDTO>()
                if (body.email.isEmpty() || body.password.isEmpty() ||
                    body.fullName.isEmpty() ||
                    body.phoneNumber.isEmpty()
                ) {
                    call.respond(
                        status = HttpStatusCode.Conflict,
                        message = mapOf(
                            "message" to "data must not be empty"
                        )
                    )
                } else {
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
