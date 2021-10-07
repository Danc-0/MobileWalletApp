package com.danc.mobilewallet.presentation

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Request.BalanceRequest
import com.danc.mobilewallet.domain.models.Response.LoginResponse
import com.danc.mobilewallet.presentation.viewmodels.BalanceViewModel
import com.danc.mobilewallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.sendMoney
import kotlinx.android.synthetic.main.fragment_send_money.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
@FragmentScoped
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val TAG = "HomeFragment"
    private val viewModel: BalanceViewModel by viewModels()
    var bundle: Bundle? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bundle = arguments
        assert(bundle != null)
        val loginResponse: LoginResponse = bundle!!.getParcelable("LoginData")!!
        greetingsTv.text = "Welcome ${loginResponse.CustomerName}"

        balance.setOnClickListener {
            findNavController(requireView()).navigate(R.id.to_balanceFragment)
        }

        sendMoney.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("LoginData", loginResponse)
            findNavController(requireView()).navigate(R.id.to_sendMoneyFragment2, bundle)
        }

        statements.setOnClickListener {
            findNavController(requireView()).navigate(R.id.to_view_miniStatementFragment)
        }

        lastTransactions.setOnClickListener {
            findNavController(requireView()).navigate(R.id.to_last_transactionsFragment)
        }

        profile.setOnClickListener {
            findNavController(requireView()).navigate(R.id.to_profileFragment)
        }

        logOut.setOnClickListener {
//            findNavController(requireView()).navigate(R.id.to_loginFragment)
            findNavController().popBackStack(R.id.loginFragment, false)
            Toast.makeText(context, "Logging you out", Toast.LENGTH_SHORT).show()
        }
    }
}