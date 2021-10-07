package com.danc.mobilewallet.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.View
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Response.LoginResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginResponse: LoginResponse? = getPref("LoginData", LoginResponse::class.java, context)

        setData(loginResponse)

    }

    private fun setData(loginResponse: LoginResponse?){
        if (loginResponse != null) {
            names.text = loginResponse.CustomerName
            email.text = loginResponse.email
            customerID.text = loginResponse.CustomerID
//            customerAccount.text = loginResponse.CustomerAccount
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