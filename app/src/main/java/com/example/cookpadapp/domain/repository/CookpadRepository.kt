package com.example.cookpadapp.domain.repository


import com.example.cookpadapp.internet.CookpadInterface
import com.example.cookpadapp.domain.utils.Result
import com.example.cookpadapp.model.RecipeDetailsResponse
import com.example.cookpadapp.model.RecipeResponse

class CookpadRepository(
    private val cookpadInterface: CookpadInterface
) {

    suspend fun getRecipes(): Result<RecipeResponse> {
        return try {
            val response = cookpadInterface.getRecipes()
            Result.Success(response)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
    suspend fun getRecipeDetails(recipeId: Int): Result<RecipeDetailsResponse> {
        return try {
            val response = cookpadInterface.getRecipeDetails(recipeId)
            Result.Success(response)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

}