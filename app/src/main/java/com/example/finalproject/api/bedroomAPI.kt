package com.example.finalproject.api

import com.example.finalproject.entity.Bedroom
import com.example.finalproject.respone.bedroomrespone.AddBedroomResponse
import com.example.finalproject.respone.bedroomrespone.DeleteBedroomResponse
import com.example.finalproject.respone.bedroomrespone.GetAllBedroomResponse
import com.example.finalproject.respone.bedroomrespone.UpdateBedroomResponse
import retrofit2.Response
import retrofit2.http.*

interface bedroomAPI {
    // for insert of bed items
    @POST("/bed/insert")
    suspend fun addbeedroom(
            @Header("Authorization") token: String,
            @Body Bedroom: Bedroom
    ): Response<AddBedroomResponse>

    //get all data
    @GET("/bed/all")
    suspend fun getAllBedroom(
            @Header("Authorization") token: String,
    ): Response<GetAllBedroomResponse>

    //delete bed items
    @DELETE("/bed/delete")
    suspend fun deleteBedroom(
            @Header("Authorization") token: String,
            @Path("id") id: String
    ): Response<DeleteBedroomResponse>

    //update bed items
    @PUT("bed/update")
    suspend fun updateBedroom(

            @Header("Authorization") token: String,
            @Path("id") id: String
    ): Response<UpdateBedroomResponse>


}