package com.example.loginpage.API

import com.google.gson.annotations.SerializedName

data class VideosData(
    @SerializedName("responsecode")
    val responseCode: Int,
    @SerializedName("response")
    val response: List<Data>
)

data class Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("subject_id")
    val subjectId: Int,
    @SerializedName("subject_name")
    val subjectName: String,
    @SerializedName("target_student")
    val targetStudent: Int,
    @SerializedName("video_link")
    val videoLink: String,
    @SerializedName("date_time")
    val dateTime: String
)
