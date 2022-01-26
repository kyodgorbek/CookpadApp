package com.example.cookpadapp.internet

import CookpadResponse
import retrofit2.Response

interface CookpadInterface {

    suspend fun getCookpad(): Response<CookpadResponse>

}