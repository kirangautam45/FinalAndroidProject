package com.example.finalproject.api

interface UserAPI {
    @post("user/insert"):Respone<addUserRespone>
}