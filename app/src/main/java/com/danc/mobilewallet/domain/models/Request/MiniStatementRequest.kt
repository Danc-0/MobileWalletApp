package com.danc.mobilewallet.domain.models.Request

data class MiniStatementRequest(
    val accountNo: String,
    val customerId: String
)