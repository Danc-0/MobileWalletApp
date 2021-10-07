package com.danc.mobilewallet.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Request.MiniStatementRequest
import com.danc.mobilewallet.presentation.viewmodels.HomeViewModel
import com.danc.mobilewallet.presentation.viewmodels.MiniStatementViewModel
import com.danc.mobilewallet.utils.State
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class MiniStatementFragment : Fragment(R.layout.fragment_mini_statement) {

    private val TAG = "MiniStatementFragment"
    private val viewModel: MiniStatementViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val miniStatementRequest = MiniStatementRequest(
            "",
            ""
        )

        lifecycleScope.launch {
            viewModel.getTransMiniStatement(miniStatementRequest).onEach {
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