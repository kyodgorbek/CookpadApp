package com.example.cookpadapp.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String
)