package com.example.retrofitpost.Model.Retrofit

import com.example.retrofitpost.Model.Retrofit.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SimpleApi {
    @GET("comments/2")
    suspend fun getpost():Response<Post>
    @POST("comments")
    suspend fun pushpost(@Body post: Post):Response<Post>
}