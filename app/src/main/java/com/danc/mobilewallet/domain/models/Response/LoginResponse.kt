package com.danc.mobilewallet.domain.models.Response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("Customer Account")
    val CustomerAccount: String,
    @SerializedName("Customer ID")
    val CustomerID: String,
    @SerializedName("Customer Name")
    val CustomerName: String,

    val email: String
) : Parcelable