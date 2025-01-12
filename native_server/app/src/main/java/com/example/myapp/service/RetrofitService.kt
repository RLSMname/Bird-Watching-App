package com.example.myapp.service

import com.example.myapp.birds.domain.model.Bird
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RetrofitService {
    @GET("api/plants")
    suspend fun getAllBirds(): List<Bird>

    @GET("api/plants/{id}")
    suspend fun getBirdById(@Path("id") id: String): Bird

    @POST("api/plants")
    suspend fun addBird(@Body bird: Bird, @Header("session-id") sessionId: String): Bird

    @DELETE("api/plants/{id}")
    suspend fun deleteBird(@Path("id") id: String, @Header("session-id") sessionId: String): Unit

    @PUT("api/plants")
    suspend fun updateBird(@Body bird: Bird, @Header("session-id") sessionId: String): Bird
}