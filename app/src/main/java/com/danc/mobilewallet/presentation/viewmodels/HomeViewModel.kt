package com.danc.mobilewallet.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.danc.mobilewallet.domain.models.Request.BalanceRequest
import com.danc.mobilewallet.domain.use_case.BalanceUseCase
import com.danc.mobilewallet.domain.use_case.LoginUseCase
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val balanceUseCase: BalanceUseCase) : ViewModel() {

    fun postToGetBalance(balanceRequest: BalanceRequest) = flow {
        emit(State.LoadingState)
        try {
            emit(State.DataState(balanceUseCase(balanceRequest)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(e.localizedMessage)
        }
    }


}