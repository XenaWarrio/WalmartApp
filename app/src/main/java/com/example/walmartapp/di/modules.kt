package com.example.walmartapp.di

import com.example.walmartapp.data.CountriesRepository
import com.example.walmartapp.data.remote.NetworkDataStore
import com.example.walmartapp.data.remote.WalmartApi
import com.example.walmartapp.presentation.viewModel.CountriesViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ManualDIModule {

    /** Instances declared here will be Singleton
     * and will stay alive during whole livecycle of app **/
    private val retrofit: Retrofit by lazy {
        provideRetrofit()
    }
    private val walmartApi: WalmartApi by lazy {
        provideApiService(retrofit)
    }
    private val networkDataStore: NetworkDataStore by lazy {
        NetworkDataStore(walmartApi)
    }
    val countriesRepository: CountriesRepository by lazy {
        CountriesRepository(networkDataStore)
    }
}

fun provideRetrofit(): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideApiService(retrofit: Retrofit): WalmartApi {
    return retrofit.create(WalmartApi::class.java)
}