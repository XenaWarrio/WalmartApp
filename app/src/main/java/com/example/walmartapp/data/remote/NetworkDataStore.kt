package com.example.walmartapp.data.remote

class NetworkDataStore(private val api: WalmartApi) {

    suspend fun getCountries() = safeApiCall { api.getCountries() }
}