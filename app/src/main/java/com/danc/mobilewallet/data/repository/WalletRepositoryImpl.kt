package com.danc.mobilewallet.data.repository

import com.danc.mobilewallet.data.remote.WalletApi
import com.danc.mobilewallet.domain.models.Request.*
import com.danc.mobilewallet.domain.models.Response.*
import com.danc.mobilewallet.domain.repository.WalletRepository
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(val walletApi: WalletApi) : WalletRepository {

    override suspend fun customerLogin(loginRequest: LoginRequest): LoginResponse {
        return walletApi.loginCustomer(loginRequest)
    }

    override suspend fun getCustomerBalance(balanceRequest: BalanceRequest): BalanceResponse {
        return walletApi.getBalance(balanceRequest)
    }

    override suspend fun sendMoney(sendMoneyRequest: SendMoneyRequest): SendMoneyResponse {
        return walletApi.sendMoney(sendMoneyRequest)
    }

    override suspend fun lastTransactions(lastTransactionRequest: LastTransactionRequest): LastTransactionsResponse {
        return walletApi.getLast100Transactions(lastTransactionRequest)
    }

    override suspend fun miniStatement(miniStatementRequest: MiniStatementRequest): MiniStatementResponse {
        return walletApi.getMiniStatement(miniStatementRequest)
    }

    override suspend fun allTransactions(): AllTransactions {
        return walletApi.getAllTransactions()
    }


}