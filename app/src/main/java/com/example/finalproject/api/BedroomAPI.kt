package com.example.finalproject.api

import com.example.finalproject.entity.Bedroom
import com.example.finalproject.response.bedroomrespone.AddBedroomResponse
import com.example.finalproject.response.bedroomrespone.DeleteBedroomResponse
import com.example.finalproject.response.bedroomrespone.GetAllBedroomResponse
import com.example.finalproject.response.bedroomrespone.UpdateBedroomResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface BedroomAPI {
    // for insert of bed items
    @POST("/bed/insert")
    suspend fun insertBeedroom(
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


    @Multipart
    @POST("bed/insert")
    suspend fun addProduct(
            @Part("bedname") bedname: RequestBody,
            @Part("size") size: RequestBody,
            @Part("cost") cost: RequestBody,
            @Part("material") material: RequestBody,
            @Part("describe") describe: RequestBody,
            @Part("image") image: MultipartBody.Part
    ): Response<AddBedroomResponse>

    @Multipart
    @POST("bed/insert")
    suspend fun addProduct1(
            @PartMap mutableMap: MutableMap<String, RequestBody>
    ): Response<AddBedroomResponse>
}