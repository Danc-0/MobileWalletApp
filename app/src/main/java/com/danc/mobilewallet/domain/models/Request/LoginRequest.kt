package com.danc.mobilewallet.domain.models.Request

data class LoginRequest(
    val customerId: String,
    val pin: String
)