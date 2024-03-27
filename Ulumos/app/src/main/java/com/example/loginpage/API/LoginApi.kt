package com.example.loginpage.API

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginApi {

    @POST("/login")
    suspend fun postData(@Body request: LoginRequest): Response<UserResponse>

    @POST("/v2/studentDashboard")
    suspend fun getDash(): Response<DashData>

    @POST("/timetable")
    suspend fun getTime(): Response<TimeData>

    @POST("/studentClassVideos")
    suspend fun getVideos(): Response<VideosData>

    @POST("/v2/getAssignments/{subject_id}")
    suspend fun getAssign(@Path("subject_id") subjectId: String): Response<AssignData>

    @POST("/status/{status}/{assignment_id}/{subject_id}")
    suspend fun postStatus(@Path("status") status: String,@Path("assignment_id") assignmentId: String,@Path("subject_id") subjectId: String): Response<StatusData>

    @POST("/completeAssignment/{assignment_id}/{subject_id}/{description}")
    suspend fun postComplete(@Path("assignment_id") assignmentId: String,@Path("subject_id") subjectId: String,@Path("description") description: String): Response<StatusData>

    @POST("/v2/studentAssignments")
    suspend fun getSubjects(): Response<SubjectsData>
}