package com.example.finalproject.response.orderresponse

import com.example.finalproject.entity.Bedroom

data class GetAllOrderResponse(
        val success: Boolean? = null,
        val count: Int? = null,
        val data: MutableList<Bedroom>? = null
)
