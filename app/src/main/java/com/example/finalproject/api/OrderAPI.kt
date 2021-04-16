package com.example.finalproject.api

import com.example.finalproject.entity.Order
import com.example.finalproject.response.orderresponse.AddOrderResponse
import com.example.finalproject.response.orderresponse.DeleteOrderResponse
import com.example.finalproject.response.orderresponse.GetAllOrderResponse
import com.example.finalproject.response.orderresponse.UpdateOrderResponse

import retrofit2.Response
import retrofit2.http.*
interface OrderAPI {
    // for insert of bed items
    @POST("/diningroom/insert")
    suspend fun insertBeedroom(
        @Header("Authorization") token: String,
        @Body Order: Order
    ): Response<AddOrderResponse>

    //get all data
    @GET("/diningroom/all")
    suspend fun getAllBedroom(
        @Header("Authorization") token: String,
    ): Response<GetAllOrderResponse>


    //delete bed items
    @DELETE("/diningroom/delete")
    suspend fun deleteBedroom(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteOrderResponse>

    //update bed items
    @PUT("diningroom/update")
    suspend fun updateBedroom(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<UpdateOrderResponse>

}