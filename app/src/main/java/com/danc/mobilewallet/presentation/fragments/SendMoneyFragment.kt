package com.danc.mobilewallet.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Request.SendMoneyRequest
import com.danc.mobilewallet.domain.models.Response.LoginResponse
import com.danc.mobilewallet.presentation.viewmodels.SendMoneyViewModel
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_send_money.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
@FragmentScoped
class SendMoneyFragment : Fragment(R.layout.fragment_send_money) {

    private val TAG = "SendMoneyFragment"
    private val viewModel: SendMoneyViewModel by viewModels()
    var bundle: Bundle? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bundle = arguments
        assert(bundle != null)
        val loginResponse: LoginResponse = bundle!!.getParcelable("LoginData")!!

        sendMoney.setOnClickListener {
           val sendMoneyRequest = SendMoneyRequest(
               loginResponse.CustomerAccount,
               account.text.toString(),
               amount.text.toString().toDouble(),
               loginResponse.CustomerID
           )

           lifecycleScope.launch {
               viewModel.postToSendMoney(sendMoneyRequest).collect {
                   when (it) {
                       is State.DataState<*> -> {
                           if (it.data.toString().isNotEmpty()){
                               Toast.makeText(context, "Money Send Successfully", Toast.LENGTH_SHORT).show()
                           }
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
}