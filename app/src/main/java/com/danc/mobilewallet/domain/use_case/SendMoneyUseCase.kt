package com.danc.mobilewallet.domain.use_case

import com.danc.mobilewallet.data.remote.WalletApi
import com.danc.mobilewallet.domain.models.Request.LoginRequest
import com.danc.mobilewallet.domain.models.Request.SendMoneyRequest
import com.danc.mobilewallet.domain.models.Response.LoginResponse
import com.danc.mobilewallet.domain.models.Response.SendMoneyResponse
import com.google.gson.JsonObject
import javax.inject.Inject

class SendMoneyUseCase @Inject constructor(
    private val walletApi: WalletApi
) {
    suspend operator fun invoke(sendMoneyRequest: SendMoneyRequest): SendMoneyResponse {
        return walletApi.sendMoney(sendMoneyRequest)
    }
}