package com.danc.mobilewallet.presentation

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Request.BalanceRequest
import com.danc.mobilewallet.domain.models.Response.BalanceResponse
import com.danc.mobilewallet.domain.models.Response.LoginResponse
import com.danc.mobilewallet.presentation.viewmodels.BalanceViewModel
import com.danc.mobilewallet.utils.State
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_balance.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
@FragmentScoped
class BalanceFragment : Fragment(R.layout.fragment_balance) {

    private val TAG = "BalanceFragment"
    private val viewModel: BalanceViewModel by viewModels()
    var bundle: Bundle? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginResponse: LoginResponse = getPref("LoginData", LoginResponse::class.java, context)!!

        val balanceRequest = BalanceRequest(
            loginResponse.CustomerID,
        )

        lifecycleScope.launch {
            viewModel.postToGetBalance(balanceRequest).collect {
                when (it) {
                    is State.DataState<*> -> {
                        var response: BalanceResponse = it.data as BalanceResponse
                        balance.text = response.balance.toString()
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

    fun getPref(key: String?, context: Context?): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(key, null)
    }

    fun <T> getPref(key: String?, t: Class<T>, context: Context?): T? {
        val data: String? = getPref(key, context)
        return if (data != null) {
            Gson().fromJson(data, t)
        } else {
            try {
                t.newInstance()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
                null
            } catch (e: java.lang.InstantiationException) {
                e.printStackTrace()
                null
            }
        }
    }
}