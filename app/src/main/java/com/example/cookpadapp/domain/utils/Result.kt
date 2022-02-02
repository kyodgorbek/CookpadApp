package com.example.cookpadapp.domain.utils

sealed class Result<out T> {
    data class Success<T>(val payload: T) : Result<T>()
    data class Error<T>(val exception: Exception) : Result<T>()
}

fun <T> Result<T>.onSuccess(payload: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        payload(this.payload)
    }
    return this
}

fun <T> Result<T>.fold(payload: (T) -> Unit, error: (Exception) -> Unit) {
    if (this is Result.Success)
        payload(this.payload)
    else if (this is Result.Error)
        error(this.exception)
}