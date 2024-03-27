package com.example.loginpage.API

import com.google.gson.annotations.SerializedName

data class TimeData(
    @SerializedName("responsecode")
    val responseCode : Int,
    @SerializedName("response")
    val response : List<Table>
)

data class Table(
    @SerializedName("time")
    val time : String,
    @SerializedName("className")
    val className : String
)