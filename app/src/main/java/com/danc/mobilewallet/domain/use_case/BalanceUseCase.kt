package com.danc.mobilewallet.domain.use_case

import com.danc.mobilewallet.data.remote.WalletApi
import com.danc.mobilewallet.domain.models.Request.BalanceRequest
import com.danc.mobilewallet.domain.models.Request.LoginRequest
import com.danc.mobilewallet.domain.models.Response.BalanceResponse
import com.danc.mobilewallet.domain.models.Response.LoginResponse
import javax.inject.Inject

class BalanceUseCase @Inject constructor(
    private val walletApi: WalletApi
) {

    suspend operator fun invoke(balanceRequest: BalanceRequest): BalanceResponse {
        return walletApi.getBalance(balanceRequest)
    }

}