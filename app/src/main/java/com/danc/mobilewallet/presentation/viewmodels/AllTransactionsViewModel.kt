package com.danc.mobilewallet.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.danc.mobilewallet.domain.use_case.AllTransactionsUseCase
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class AllTransactionsViewModel @Inject constructor(private val allTransactionsUseCase: AllTransactionsUseCase) : ViewModel() {

    fun getAllTransactions() = flow {
        emit(State.LoadingState)
        try {
            emit(State.DataState(allTransactionsUseCase()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(e.localizedMessage)
        }
    }


}