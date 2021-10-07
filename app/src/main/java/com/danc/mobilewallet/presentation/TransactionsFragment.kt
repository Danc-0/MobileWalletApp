package com.danc.mobilewallet.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.danc.mobilewallet.R
import com.danc.mobilewallet.presentation.viewmodels.LastTransactionsViewModel
import com.danc.mobilewallet.utils.State
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TransactionsFragment : Fragment(R.layout.fragment_transactions) {

    private val TAG = "StatementFragment"
    private val viewModelLast: LastTransactionsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        lifecycleScope.launch {
//            viewModelLast.getAllTransactions().onEach {
//                when (it) {
//                    is State.DataState<*> -> {
//                        Log.d(TAG, "onCreate: ${it.data}")
//                    }
//                    is State.ErrorState -> {
//                        Log.d(TAG, "onCreate1: ${it.exception}")
//
//                    }
//                    is State.LoadingState -> {
//                        Log.d(TAG, "onCreate2: Loading")
//
//                    }
//                }
//            }
//        }

    }
}