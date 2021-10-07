package com.danc.mobilewallet.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Request.MiniStatementRequest
import com.danc.mobilewallet.domain.models.Response.LastTransactionsResponseItem
import com.danc.mobilewallet.domain.models.Response.MiniStatementResponseItem
import com.danc.mobilewallet.presentation.adapter.MiniTransactionsAdapter
import com.danc.mobilewallet.presentation.adapter.TransactionsAdapter
import com.danc.mobilewallet.presentation.viewmodels.MiniStatementViewModel
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_mini_statement.*
import kotlinx.android.synthetic.main.fragment_transactions.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
@FragmentScoped
class MiniStatementFragment : Fragment(R.layout.fragment_mini_statement) {

    private val TAG = "MiniStatementFragment"
    private val viewModel: MiniStatementViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateRecycler()

    }

    private fun populateRecycler() {
        val miniStatementRequest = MiniStatementRequest(
            "ACT1494",
            "CUST1494"
        )

        lifecycleScope.launch {
            viewModel.getTransMiniStatement(miniStatementRequest).collect {
                when (it) {
                    is State.DataState<*> -> {
                        val lastTrans: List<MiniStatementResponseItem> =
                            it.data as List<MiniStatementResponseItem>
                        if (lastTrans.isNotEmpty()) {
                            val adapter =
                                MiniTransactionsAdapter(lastTrans)
                            rvMiniTrans.layoutManager = LinearLayoutManager(context)
                            rvMiniTrans.adapter = adapter
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