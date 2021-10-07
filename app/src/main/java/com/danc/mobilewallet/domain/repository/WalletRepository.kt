package com.danc.mobilewallet.domain.repository

import com.danc.mobilewallet.domain.models.Request.*
import com.danc.mobilewallet.domain.models.Response.*

interface WalletRepository {

    suspend fun customerLogin(loginRequest: LoginRequest): LoginResponse

    suspend fun getCustomerBalance(balanceRequest: BalanceRequest): BalanceResponse

    suspend fun sendMoney(sendMoneyRequest: SendMoneyRequest): SendMoneyResponse

    suspend fun lastTransactions(lastTransactionRequest: LastTransactionRequest): LastTransactionsResponse

    suspend fun miniStatement(miniStatementRequest: MiniStatementRequest): MiniStatementResponse

    suspend fun allTransactions(): AllTransactions


}