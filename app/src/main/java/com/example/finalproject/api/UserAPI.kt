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
    //insert user information
    @POST("user/insert/")
    suspend fun addUser(
            @Header("Authorization") token: String,
            @Body user: User

    ):Response<AddUserRespone>

    //get all user information
@GET("user/all/")
suspend fun getAllUser(
        @Header("Authorization") token:String,
):Response<GetAllUserResponse>

//delete user
@DELETE("user/delete/")
suspend fun deleteuser(
        @Header("Authorization")token: String,
        @Path("id")id:String
):Response<DeleteUserRespone>
//user information update
@PUT("Authorization")
suspend fun updateUser(

)
}