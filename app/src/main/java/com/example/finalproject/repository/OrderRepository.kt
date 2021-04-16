package com.example.finalproject.repository

import com.example.finalproject.api.MyApiRequest
import com.example.finalproject.api.OrderAPI
import com.example.finalproject.api.ServiceBuilder
import com.example.finalproject.entity.Bedroom
import com.example.finalproject.response.bedroomrespone.AddBedroomResponse
import com.example.finalproject.response.bedroomrespone.DeleteBedroomResponse
import com.example.finalproject.response.bedroomrespone.GetAllBedroomResponse
import com.example.finalproject.entity.Order
import com.example.finalproject.response.orderresponse.AddOrderResponse
import com.example.finalproject.response.orderresponse.DeleteOrderResponse
import com.example.finalproject.response.orderresponse.GetAllOrderResponse

class OrderRepository:MyApiRequest() {
    private val OrderAPI =
        ServiceBuilder.buildService(OrderAPI::class.java)

    suspend fun insetrOrder(order: Order): AddOrderResponse {
        return apiRequest {
            OrderAPI.insertOrder(ServiceBuilder.token!!, order)
        }

    }


    suspend fun getAllorder(): GetAllOrderResponse {
        return apiRequest {
            OrderAPI.getAllOrder(ServiceBuilder.token!!)

        }

    }

    suspend fun deleteorder(id: String): DeleteOrderResponse {
        return apiRequest {
            OrderAPI.deleteOrder(ServiceBuilder.token!!, id)
        }
    }
}