package com.example.finalproject.api

import com.example.finalproject.respone.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface User {
    @POST ("/user/insert")
    suspend fun registerUser(
        @Body user: User
    ):Response<LoginResponse>
@FormUrlEncoded
@POST("/user/login")
suspend fun checkUser(
    @Field("username") phone:String,
    @Field("password") password:String
):Response<LoginResponse>

}