package com.example.loginpage.API

import com.google.gson.annotations.SerializedName

data class StatusData(
    @SerializedName("responsecode")
    val responseCode: Int,
    @SerializedName("response")
    val response: List<Any> = emptyList()
)
