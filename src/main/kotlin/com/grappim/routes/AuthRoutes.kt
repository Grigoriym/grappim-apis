package com.grappim.routes

import com.grappim.EMAIL_PATTERN
import com.grappim.data.SignInDataDTO
import com.grappim.data.SignInDataErrorDTO
import com.grappim.data.SignUpDataDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes() {
    post("/signin") {
        val body = call.receive<SignInDataDTO>()
        val emailMessage = checkEmail(body.email)
        val passwordMessage = checkPassword(body.password)

        if (emailMessage == null && passwordMessage == null) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                status = HttpStatusCode.Conflict,
                message = SignInDataErrorDTO(
                    emailError = emailMessage,
                    passwordError = passwordMessage
                )
            )
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

private fun checkEmail(email: String): String? =
    when {
        email.isEmpty() -> {
            "email must not be empty"
        }

        EMAIL_PATTERN.matcher(email).matches().not() -> {
            "email must be valid"
        }

        else -> {
            null
        }
    }

private fun checkPassword(password: String): String? =
    when {
        password.isEmpty() -> {
            "password must not be empty"
        }

        else -> {
            null
        }
    }
