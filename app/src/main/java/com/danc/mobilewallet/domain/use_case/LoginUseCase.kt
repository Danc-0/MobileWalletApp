package com.danc.mobilewallet.domain.use_case

import com.danc.mobilewallet.data.remote.WalletApi
import com.danc.mobilewallet.domain.models.Request.LoginRequest
import com.danc.mobilewallet.domain.models.Response.LoginResponse
import com.google.gson.JsonObject
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val walletApi: WalletApi
) {
    suspend operator fun invoke(loginRequest: LoginRequest): LoginResponse {
        return walletApi.loginCustomer(loginRequest)
    }
}