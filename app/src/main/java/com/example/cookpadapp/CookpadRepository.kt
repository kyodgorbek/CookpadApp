package com.example.cookpadapp

import com.example.cookpadapp.internet.CookpadInterface

class CookpadRepository(
    private val cookpadInterface: CookpadInterface
) {

    suspend fun getCookpad() = cookpadInterface.getCookpad()

}