package com.example.finalproject.response.bedroomrespone

import com.example.finalproject.entity.Bedroom

data class bedroomResponse(
        val success: Boolean? = null,
        val data: MutableList<Bedroom>? = null
)
