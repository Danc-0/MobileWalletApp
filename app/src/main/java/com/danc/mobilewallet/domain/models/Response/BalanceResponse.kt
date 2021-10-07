package com.danc.mobilewallet.domain.models.Response

data class BalanceResponse(
    val accountNo: String,
    val balance: Double
)