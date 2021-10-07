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
import com.danc.mobilewallet.domain.models.Request.SendMoneyRequest
import com.danc.mobilewallet.presentation.viewmodels.HomeViewModel
import com.danc.mobilewallet.presentation.viewmodels.SendMoneyViewModel
import com.danc.mobilewallet.utils.State
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class SendMoneyFragment : Fragment(R.layout.fragment_send_money) {

    private val TAG = "SendMoneyFragment"
    private val viewModel: SendMoneyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sendMoneyRequest = SendMoneyRequest(
            "",
            "",
            0.0,
            ""
        )

        lifecycleScope.launch {
            viewModel.postToSendMoney(sendMoneyRequest).onEach {
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