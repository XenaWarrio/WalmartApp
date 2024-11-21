package com.example.walmartapp.data.remote

import java.io.IOException
import com.example.walmartapp.utils.Result
import retrofit2.HttpException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        val response = apiCall()
        Result.Success(response)
    } catch (exception: Throwable) {
        when (exception) {
            is IOException -> Result.Error("Network error occurred", exception)
            is HttpException -> {
                val errorMessage = exception.response()?.errorBody()?.string() ?: "Unknown error"
                Result.Error(errorMessage, exception)
            }
            else -> Result.Error("Unexpected error occurred", exception)
        }
    }
}