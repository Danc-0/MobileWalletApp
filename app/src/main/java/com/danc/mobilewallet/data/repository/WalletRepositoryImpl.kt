package com.danc.mobilewallet.data.repository

import com.danc.mobilewallet.data.remote.WalletApi
import com.danc.mobilewallet.domain.repository.WalletRepository
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(walletApi: WalletApi) : WalletRepository() {
}