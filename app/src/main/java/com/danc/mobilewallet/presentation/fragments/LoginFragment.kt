package com.danc.mobilewallet.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Request.LoginRequest
import com.danc.mobilewallet.domain.models.Response.LoginResponse
import com.danc.mobilewallet.presentation.viewmodels.LoginViewModel
import com.danc.mobilewallet.utils.State
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
@FragmentScoped
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val TAG = "LoginFragment"
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_login.setOnClickListener {
            if (tv_account_number.text.isNullOrEmpty() && tv_pwd.text.isNullOrEmpty()){
                Toast.makeText(context, "Enter your credentials", Toast.LENGTH_SHORT).show()
            }

            val loginRequest = LoginRequest(
                tv_account_number.text.toString(),
                tv_pwd.text.toString()
            )

            lifecycleScope.launch {
                loginViewModel.postToLogin(loginRequest)
                    .collect {
                        when (it) {
                            is State.DataState<*> -> {
                                Log.d(TAG, "onViewCreated: ${it.data}")
                                val loginResponse : LoginResponse = it.data as LoginResponse
                                if (loginResponse != null) {
                                    val bundle = Bundle()
                                    Log.d(TAG, "onViewCreated: $loginResponse")
                                    bundle?.putParcelable("LoginData", loginResponse)
                                    Log.d(TAG, "onViewCreated: $bundle")
                                    storePrefs("LoginData", loginResponse, context)
                                    Navigation.findNavController(requireView()).navigate(R.id.to_homeFragment, bundle)

                                }
                            }
                            is State.ErrorState -> {
                                Toast.makeText(context, "${it.exception}", Toast.LENGTH_SHORT).show()

                            }
                            is State.LoadingState -> {
                                Toast.makeText(context, "Loading. Please wait", Toast.LENGTH_SHORT).show()

                            }
                        }
                    }
            }
        }
    }

    fun storePrefs(key: String, value: Any?, context: Context?) {
        try {
            val store = Gson().toJson(value)
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = prefs.edit()
            editor.putString(key, store)
            editor.apply()
        } catch (se: Exception) {
            Log.d("storePref", se.toString())
        }
    }

}