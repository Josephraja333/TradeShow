package com.example.loginpage.API

import com.google.gson.annotations.SerializedName

data class SubjectsData(
    @SerializedName("responsecode")
    val responseCode : Int,
    @SerializedName("response")
    val response : List<Subjects>
)

data class Subjects(
    @SerializedName("subject_id")
    val subjectId : Int,
    @SerializedName("subject_name")
    val subjectName : String,
)
