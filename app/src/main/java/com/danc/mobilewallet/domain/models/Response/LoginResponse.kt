package com.danc.mobilewallet.domain.models.Response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("Customer Account")
    val CustomerAccount: String,
    @SerializedName("Customer ID")
    val CustomerID: String,
    @SerializedName("Customer Name")
    val CustomerName: String,

    val email: String
)