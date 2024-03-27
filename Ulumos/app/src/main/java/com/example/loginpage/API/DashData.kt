package com.example.loginpage.API

import com.google.gson.annotations.SerializedName

data class DashData(
    @SerializedName("responsecode")
    val responseCode: Int,
    @SerializedName("assignments")
    val assignments: Assignments,
    @SerializedName("details")
    val details: List<Detail>
)

data class Assignments(
    @SerializedName("total_assignments")
    val totalAssignments: Int,
    @SerializedName("completed_assignments")
    val completedAssignments: Int,
    @SerializedName("incomplete_assignments")
    val incompleteAssignments: Int
)

data class Detail(
    @SerializedName("subject_code")
    val subjectCode: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("subject_name")
    val subjectName: String,
    @SerializedName("subject_completed")
    val subjectCompleted: Int,
    @SerializedName("subject_incomplete")
    val subjectIncomplete: Int
)

