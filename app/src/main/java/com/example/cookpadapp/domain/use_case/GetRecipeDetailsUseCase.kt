package com.example.cookpadapp.domain.use_case

import com.example.cookpadapp.domain.repository.CookpadRepository

class GetRecipeDetailsUseCase(
    private val cookpadRepository: CookpadRepository
) {

    suspend operator fun invoke(recipeId: Int) = cookpadRepository.getRecipeDetails(recipeId)
}