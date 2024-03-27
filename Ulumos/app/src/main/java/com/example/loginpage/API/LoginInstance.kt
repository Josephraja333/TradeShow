package com.example.loginpage.API

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object LoginInstance {
    var authToken: String = ""

    val api: LoginApi by lazy {

        val okHttpLoggingInterceptor = HttpLoggingInterceptor()
        okHttpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Authorization", authToken)
                .method(original.method, original.body)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }).build()

        Retrofit.Builder().baseUrl("http://10.52.0.250:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(LoginApi::class.java)
    }
}