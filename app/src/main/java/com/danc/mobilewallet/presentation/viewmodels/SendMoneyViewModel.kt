package com.danc.mobilewallet.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.danc.mobilewallet.domain.models.Request.SendMoneyRequest
import com.danc.mobilewallet.domain.use_case.LoginUseCase
import com.danc.mobilewallet.domain.use_case.SendMoneyUseCase
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SendMoneyViewModel @Inject constructor(private val sendMoneyUseCase: SendMoneyUseCase) : ViewModel() {

    fun postToSendMoney(sendMoneyRequest: SendMoneyRequest) = flow {
        emit(State.LoadingState)
        try {
            emit(State.DataState(sendMoneyUseCase(sendMoneyRequest)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(e.localizedMessage)
        }
    }


}