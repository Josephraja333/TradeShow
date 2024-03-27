package com.example.loginpage.API

import com.google.gson.annotations.SerializedName

data class AssignData(
    @SerializedName("responsecode")
    val responseCode: Int,
    @SerializedName("response")
    val response: List<Assignment>
)

data class Assignment(
    @SerializedName("assi_id")
    val assignmentId: Int,
    @SerializedName("assi_name")
    val assignmentName: String,
    @SerializedName("faculty_id")
    val facultyId: Int,
    @SerializedName("faculty_name")
    val facultyName: String,
    @SerializedName("faculty_image")
    val facultyImage: String,
    @SerializedName("subject_id")
    val subjectId: Int,
    @SerializedName("subject_name")
    val subjectName: String,
    @SerializedName("assi_description")
    val assignmentDescription: String,
    @SerializedName("deadline")
    val deadline: String,
    @SerializedName("difficulty_level")
    val difficultyLevel: String,
    @SerializedName("target_students")
    val targetStudents: Int,
    @SerializedName("date_time")
    val dateTime: String,
    @SerializedName("prerequisite_assignments")
    val prerequisiteAssignments: List<String>,
    @SerializedName("assignment_status")
    val assignmentStatus: String?
)
