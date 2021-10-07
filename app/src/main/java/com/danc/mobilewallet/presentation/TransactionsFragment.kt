package com.danc.mobilewallet.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Request.LastTransactionRequest
import com.danc.mobilewallet.domain.models.Response.LastTransactionsResponseItem
import com.danc.mobilewallet.domain.models.Response.LoginResponse
import com.danc.mobilewallet.presentation.adapter.TransactionsAdapter
import com.danc.mobilewallet.presentation.viewmodels.LastTransactionsViewModel
import com.danc.mobilewallet.utils.State
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_transactions.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
@FragmentScoped
class TransactionsFragment : Fragment(R.layout.fragment_transactions) {

    private val TAG = "StatementFragment"
    private val viewModelLast: LastTransactionsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginResponse: LoginResponse? = getPref("LoginData", LoginResponse::class.java, context)

        val lastTransactions = loginResponse?.CustomerID?.let {
            LastTransactionRequest(
                it
            )
        }

        lifecycleScope.launch {
            if (lastTransactions != null) {
                viewModelLast.getLastTransactions(lastTransactions).collect {
                    when (it) {
                        is State.DataState<*> -> {
                        val lastTrans: List<LastTransactionsResponseItem> = it.data as List<LastTransactionsResponseItem>
                            if (lastTrans.isNotEmpty()) {
                                val adapter =
                                    TransactionsAdapter(lastTrans)
                                rvTransactions.layoutManager = LinearLayoutManager(context)
                                rvTransactions.adapter = adapter
                                calculateTotal(lastTrans)
                            } else {
                                amountTotal.isInvisible
                                totals.text = "You do not have any transaction"
                            }
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

    fun calculateTotal(lastTransactionsResponseItem: List<LastTransactionsResponseItem>): Int? {
        var totalAmount: Int = 0

        for (lastItem: LastTransactionsResponseItem in lastTransactionsResponseItem) {
            totalAmount += lastItem.amount.toInt()
            amountTotal.text = totalAmount.toString()
        }
        return totalAmount
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