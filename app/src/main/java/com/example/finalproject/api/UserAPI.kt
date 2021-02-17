package com.example.finalproject.api

import android.media.session.MediaSession
import androidx.room.Delete
import com.example.finalproject.entity.User
import com.example.finalproject.respone.AddUserRespone
import com.example.finalproject.respone.DeleteUserRespone
import com.example.finalproject.respone.GetAllUserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    @POST("user/insert/")
    suspend fun addUser(
            @Header("Authorization") token: String,
            @Body user: User

    ):Response<AddUserRespone>
@GET("user/update/")
suspend fun getAllUser(
        @Header("Authorization") token:String,
):Response<GetAllUserResponse>
@Delete("user/delete/{id}")
suspend fun deleteuser(
        @Header("Authrozation")token: String,
        @Path("id")id:String
):Response<DeleteUserRespone>

}