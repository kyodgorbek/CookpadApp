package com.example.cookpadapp.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookpadapp.domain.use_case.GetRecipeUseCase
import com.example.cookpadapp.domain.utils.fold
import com.example.cookpadapp.model.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CookpadViewModel(private val recipeUseCase: GetRecipeUseCase) : ViewModel() {

    var cookpad = MutableLiveData<RecipeResponse>()

    // Expose to the outside world
    val error = MutableLiveData<String>()
    var progress = MutableLiveData(false)


    init {
        getRecipes()
    }

    fun getRecipes() {
        progress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            recipeUseCase.invoke()
                .fold({ recipeResponse ->
                    cookpad.postValue(recipeResponse)
                }, {
                    error.postValue(it.message)
                })
            progress.postValue(false)
        }
    }
}
