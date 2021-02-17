package com.example.finalproject.respone

import com.example.finalproject.entity.User

class GetAllUser (
        val success:Boolean?=null,
        val count: Int?=null,
        val data:MutableList<User>?=null
        )