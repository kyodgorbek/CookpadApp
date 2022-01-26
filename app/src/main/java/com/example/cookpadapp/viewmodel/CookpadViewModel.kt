package com.example.cookpadapp.viewmodel

import CookpadResponse
import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookpadapp.CookpadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CookpadViewModel(private val cookpadRepository: CookpadRepository) : ViewModel() {

    private var _cookpadResponse = MutableLiveData<CookpadResponse>()

    // Expose to the outside world
    val cookpad: LiveData<CookpadResponse> = _cookpadResponse
    var progress: MutableLiveData<Boolean> = MutableLiveData(false)


    init {
        getCookpad()
    }

    @UiThread
    fun getCookpad() {
        viewModelScope.launch(Dispatchers.IO) {
            progress.postValue(true)
            try {
                val response = cookpadRepository.getCookpad().body()!!
                _cookpadResponse.postValue(response)
            } finally {
                progress.postValue(false)
            }
        }
    }
}