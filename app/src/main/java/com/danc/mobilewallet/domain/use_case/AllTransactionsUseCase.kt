package com.danc.mobilewallet.domain.use_case

import com.danc.mobilewallet.data.remote.WalletApi
import com.danc.mobilewallet.domain.models.Response.AllTransactions
import javax.inject.Inject

class AllTransactionsUseCase @Inject constructor(
    private val walletApi: WalletApi
) {

    suspend operator fun invoke(): AllTransactions {
        //here you can add some domain logic or call another UseCase
        return walletApi.getAllTransactions()
    }

}