package com.example.finalproject.api

import android.media.session.MediaSession
import com.example.finalproject.entity.User
import com.example.finalproject.respone.AddUserRespone
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAPI {
    @POST("user/insert")
    suspend fun addUser(
            @Header("Authorization") token: String,
            @Body user: User

    ):Response<AddUserRespone>
@GET("user/update")
suspend fun getAllUser(
        @Header("Authorization") token:String,
):Response<GetAllUserRespone>


}