package com.example.finalproject.repository

import com.example.finalproject.api.MyApiRequest
import com.example.finalproject.api.ServiceBuilder
import com.example.finalproject.api.UserAPI
import com.example.finalproject.entity.User

class UserRepository:MyApiRequest() {
    private val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    //user registration
    suspend fun registerUser(user: User):LoginResponse


}