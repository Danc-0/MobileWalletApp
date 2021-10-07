package com.danc.mobilewallet.domain.models.Response

data class AllTransactionsItem(
    val accountNo: String,
    val amount: Double,
    val balance: Double,
    val customerId: String,
    val debitOrCredit: String,
    val id: Int,
    val transactionId: String,
    val transactionType: String
)