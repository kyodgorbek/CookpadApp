package com.example.cookpadapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookpadapp.domain.use_case.GetRecipeDetailsUseCase
import com.example.cookpadapp.domain.use_case.GetRecipeUseCase
import com.example.cookpadapp.domain.utils.fold
import com.example.cookpadapp.model.RecipeDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailCookpadViewModel(
    private  val getRecipeDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModel() {

    var recipeDetails = MutableLiveData<RecipeDetailsResponse>()

    // Expose to the outside world
    private val error = MutableLiveData<String>()
    var progress = MutableLiveData(false)

    fun initData(recipeId: Int?) {
        recipeId?.let { getRecipeDetails(it) }
    }

    private fun getRecipeDetails(recipeId: Int) {
        progress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            getRecipeDetailsUseCase.invoke(recipeId)
                .fold({ recipeResponse ->
                    recipeDetails.postValue(recipeResponse)
                }, {
                    error.postValue(it.message)
                })
            progress.postValue(false)
        }
    }

}
