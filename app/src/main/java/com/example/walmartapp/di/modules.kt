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

val appModule = module {
    single { provideRetrofit() }
    single { provideApiService(get()) }
    single { NetworkDataStore(get()) }
    single { CountriesRepository(get()) }
    factory { CountriesViewModel(get()) }
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