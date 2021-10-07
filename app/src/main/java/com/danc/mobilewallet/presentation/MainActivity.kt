package com.danc.mobilewallet.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.danc.mobilewallet.R
import com.danc.mobilewallet.presentation.viewmodels.AllTransactionsViewModel
import com.danc.mobilewallet.presentation.viewmodels.LastTransactionsViewModel
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val viewModelLast: AllTransactionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            viewModelLast.getAllTransactions()
                .collect {
                when (it) {
                    is State.DataState<*> -> {
                        Log.d(TAG, "onCreate: ${it.data}")
                    }
                    is State.ErrorState -> {
                        Log.d(TAG, "onCreate1: ${it.exception}")

                    }
                    is State.LoadingState -> {
                        Log.d(TAG, "onCreate2: Loading")

                    }
                }
            }
        }
    }
}