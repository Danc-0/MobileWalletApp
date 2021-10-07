package com.danc.mobilewallet.data.remote

import com.danc.mobilewallet.domain.models.Request.*
import com.danc.mobilewallet.domain.models.Response.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface WalletApi {

    @POST("customers/login")
    suspend fun loginCustomer(loginRequest: LoginRequest) : LoginResponse

    @POST("accounts/balance")
    suspend fun getBalance(balanceRequest: BalanceRequest) : BalanceResponse

    @POST("transactions/send-money")
    suspend fun sendMoney(sendMoneyRequest: SendMoneyRequest) : SendMoneyResponse

    @POST("last-100-transactions")
    suspend fun getLast100Transactions(lastTransactionRequest: LastTransactionRequest) : LastTransactionsResponse

    @POST("transactions/mini-statement")
    suspend fun getMiniStatement(miniStatementRequest: MiniStatementRequest) : MiniStatementResponse

    @GET("transactions/")
    suspend fun getAllTransactions() : AllTransactions

}