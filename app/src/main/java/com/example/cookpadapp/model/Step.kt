package com.example.cookpadapp.model
import com.google.gson.annotations.SerializedName

data class Step(
    val description: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>
)