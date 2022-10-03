package com.grappim.data

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDataDTO(
    val fullName: String,
    val email: String,
    val password: String,
    val phoneNumber: String
)
