package com.grappim.data

import kotlinx.serialization.Serializable

@Serializable
data class SignInDataDTO(
    val email:String,
    val password:String
)
