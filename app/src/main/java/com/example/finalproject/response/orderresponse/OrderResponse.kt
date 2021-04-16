package com.example.finalproject.response.orderresponse

import com.example.finalproject.entity.Bedroom

data class OrderResponse(
        val success: Boolean? = null,
        val data: MutableList<Bedroom>? = null
)
