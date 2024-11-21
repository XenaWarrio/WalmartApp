package com.example.walmartapp.data

import com.example.walmartapp.data.remote.NetworkDataStore

class CountriesRepository(private val networkDataStore: NetworkDataStore) {

    suspend fun getCountries() = networkDataStore.getCountries()
}