package com.danc.mobilewallet.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.danc.mobilewallet.domain.models.Request.LoginRequest
import com.danc.mobilewallet.domain.use_case.LoginUseCase
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun postToLogin(loginRequest: LoginRequest) = flow {
        emit(State.LoadingState)
        try {
            emit(State.DataState(loginUseCase(loginRequest)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(e.localizedMessage)
        }
    }


}