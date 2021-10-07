package com.danc.mobilewallet.domain.models.Request

data class SendMoneyRequest(
    val accountFrom: String,
    val accountTo: String,
    val amount: Double,
    val customerId: String
)