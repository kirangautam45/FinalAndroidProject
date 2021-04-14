package com.example.furniturewear.api

import com.example.furniturewear.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface UserAPI {
    @POST("/user/login")
    suspend fun checkUser(
        @Field("phone") phone:String,
        @Field("password") password:String
    ): Response<LoginResponse>
}