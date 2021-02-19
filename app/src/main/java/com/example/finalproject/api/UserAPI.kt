package com.example.finalproject.api

import android.media.session.MediaSession
import androidx.room.Delete
import com.example.finalproject.entity.User
import com.example.finalproject.respone.AddUserRespone
import com.example.finalproject.respone.DeleteUserRespone
import com.example.finalproject.respone.GetAllUserResponse
import com.example.finalproject.respone.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    //insert user information
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