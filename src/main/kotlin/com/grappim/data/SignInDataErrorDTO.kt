package com.grappim.data

import kotlinx.serialization.Serializable

@Serializable
data class SignInDataErrorDTO(
    val emailError: String? = null,
    val passwordError: String? = null
)
