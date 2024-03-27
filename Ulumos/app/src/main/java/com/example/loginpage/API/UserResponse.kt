package com.example.loginpage.API

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("responsecode")
    val responseCode: Int,
    @SerializedName("response")
    val response: List<User>
)

data class User(
    @SerializedName("zohocorp_id")
    val zohoCorpId: String,
    @SerializedName("zohocorp_mail")
    val zohoCorpMail: String,
    @SerializedName("profile_picture")
    val profilePicture: String,
    @SerializedName("rolename")
    val roleName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("batch_name")
    val batchName: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("school_code")
    val schoolCode: String
)

