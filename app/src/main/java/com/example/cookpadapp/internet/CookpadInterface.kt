package com.example.cookpadapp.internet


import com.example.cookpadapp.model.RecipeDetailsResponse
import com.example.cookpadapp.model.RecipeResponse
import com.example.cookpadapp.utils.Constants

import retrofit2.http.GET
import retrofit2.http.Path

interface CookpadInterface {

    @GET(Constants.COOKPAD_URL)
    suspend fun getRecipes(): RecipeResponse

    @GET(Constants.RECIPE_DETAIL_URL)
    suspend fun getRecipeDetails(@Path("id") recipeId: Int): RecipeDetailsResponse


}