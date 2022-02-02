package com.example.cookpadapp.model


import com.google.gson.annotations.SerializedName

data class RecipeDetailsResponse(

    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,

    val ingredients: List<String>,
    @SerializedName("published_at")
    val publishedAt: String,

    val steps: List<Step>,

    val story: String,

    val title: String,

    val user: User
)