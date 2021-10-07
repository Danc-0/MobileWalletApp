package com.danc.mobilewallet.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.danc.mobilewallet.domain.models.Request.MiniStatementRequest
import com.danc.mobilewallet.domain.use_case.LoginUseCase
import com.danc.mobilewallet.domain.use_case.MiniStatementUseCase
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MiniStatementViewModel @Inject constructor(private val miniStatementUseCase: MiniStatementUseCase) : ViewModel() {

    fun getTransMiniStatement(miniStatementRequest: MiniStatementRequest) = flow {
        emit(State.LoadingState)
        try {
            emit(State.DataState(miniStatementUseCase(miniStatementRequest)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(e.localizedMessage)
        }
    }


}