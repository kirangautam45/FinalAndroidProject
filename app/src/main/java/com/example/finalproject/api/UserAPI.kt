package com.example.finalproject.api

import android.media.session.MediaSession
import com.example.finalproject.entity.User
import com.example.finalproject.respone.AddUserRespone
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAPI {
    @POST("user/insert")
    suspend fun addstudent(
            @Header("Authorization") token: String,
            @Body user: User

    ):Response<AddUserRespone>
}