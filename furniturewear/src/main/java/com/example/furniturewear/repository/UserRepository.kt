package com.example.furniturewear.repository

import com.example.furniturewear.api.MyApiRequest
import com.example.furniturewear.api.ServiceBuilder
import com.example.furniturewear.api.UserAPI
import com.example.furniturewear.response.LoginResponse

class UserRepository:MyApiRequest() {
    val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    suspend fun loginUser(phone:String,password:String): LoginResponse {
        return apiRequest {
            userAPI.checkUser(phone,password)
        }
    }


}