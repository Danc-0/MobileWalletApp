package com.danc.mobilewallet.data.remote

import com.danc.mobilewallet.domain.models.Request.*
import com.danc.mobilewallet.domain.models.Response.*
import retrofit2.http.*

interface WalletApi {

    @POST("customers/login")
    suspend fun loginCustomer(@Body loginRequest: LoginRequest) : LoginResponse

    @POST("accounts/balance")
    suspend fun getBalance(@Body balanceRequest: BalanceRequest) : BalanceResponse

    @POST("transactions/send-money")
    suspend fun sendMoney(@Body sendMoneyRequest: SendMoneyRequest) : SendMoneyResponse

    @POST("transactions/last-100-transactions")
    suspend fun getLast100Transactions(@Body lastTransactionRequest: LastTransactionRequest) : LastTransactionsResponse

    @POST("transactions/mini-statement")
    suspend fun getMiniStatement(@Body miniStatementRequest: MiniStatementRequest) : MiniStatementResponse

    @GET("transactions/")
    suspend fun getAllTransactions() : AllTransactions

}