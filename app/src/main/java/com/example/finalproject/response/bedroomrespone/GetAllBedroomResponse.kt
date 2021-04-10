package com.example.finalproject.response.bedroomrespone

import com.example.finalproject.entity.Bedroom

data class GetAllBedroomResponse(
        val success: Boolean? = null,
        val count: Int? = null,
        val data: MutableList<Bedroom>? = null
)
