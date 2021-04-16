package com.example.furniturewear.api

import com.example.furniturewear.entity.User
import com.example.furniturewear.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {
    //insert user information
    @POST ("/user/insert")
    suspend fun registerUser(
        @Body user: User
    ):Response<LoginResponse>
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun checkUser(
        @Field("phone") phone:String,
        @Field("password") password:String
    ):Response<LoginResponse>

}