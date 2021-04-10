package com.example.finalproject.repository

import com.example.finalproject.api.MyApiRequest
import com.example.finalproject.api.ServiceBuilder
import com.example.finalproject.entity.Bedroom
import com.example.finalproject.response.bedroomrespone.AddBedroomResponse
import com.example.finalproject.response.bedroomrespone.DeleteBedroomResponse
import com.example.finalproject.response.bedroomrespone.GetAllBedroomResponse


class BedroomRepository : MyApiRequest() {

    private val BedroomAPI =
            ServiceBuilder.buildService(com.example.finalproject.api.BedroomAPI::class.java)

    suspend fun insertBedroom(bedroom: Bedroom): AddBedroomResponse {
        return apiRequest {
            BedroomAPI.insertBeedroom(ServiceBuilder.token!!, bedroom)
        }

    }


    suspend fun getAllBedRoom(): GetAllBedroomResponse {
        return apiRequest {
            BedroomAPI.getAllBedroom(ServiceBuilder.token!!)

        }

    }

    suspend fun deleteBedroom(id: String): DeleteBedroomResponse {
        return apiRequest {
            BedroomAPI.deleteBedroom(ServiceBuilder.token!!, id)
        }
    }
}