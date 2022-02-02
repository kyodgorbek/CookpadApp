package com.example.cookpadapp.domain.use_case

import com.example.cookpadapp.domain.repository.CookpadRepository

class GetRecipeUseCase(
    private val cookpadRepository: CookpadRepository
) {

    suspend operator fun invoke() = cookpadRepository.getRecipes()
}
