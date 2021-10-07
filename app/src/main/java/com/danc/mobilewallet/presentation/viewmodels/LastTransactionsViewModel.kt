package com.danc.mobilewallet.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.danc.mobilewallet.domain.models.Request.LastTransactionRequest
import com.danc.mobilewallet.domain.use_case.LastTransactionsUseCase
import com.danc.mobilewallet.domain.use_case.LoginUseCase
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class LastTransactionsViewModel @Inject constructor(private val transactionsUseCase: LastTransactionsUseCase) : ViewModel() {

    fun getLastTransactions(lastTransactionRequest: LastTransactionRequest) = flow {
        emit(State.LoadingState)
        try {
            emit(State.DataState(transactionsUseCase(lastTransactionRequest)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(e.localizedMessage)
        }
    }


}