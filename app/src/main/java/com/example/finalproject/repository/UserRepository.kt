package com.example.finalproject.repository

import com.example.finalproject.api.MyApiRequest
import com.example.finalproject.api.ServiceBuilder
import com.example.finalproject.api.UserAPI
import com.example.finalproject.entity.User
import com.example.finalproject.respone.LoginResponse

class UserRepository:MyApiRequest() {
     val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    //user registration
    suspend fun registerUser(user: User):LoginResponse{
        return apiRequest {
            userAPI.registerUser(user)
        }
    }
suspend fun loginUser(phone:String,password:String):LoginResponse{
    return apiRequest {
        userAPI.checkUser(phone,password)
    }
}

}