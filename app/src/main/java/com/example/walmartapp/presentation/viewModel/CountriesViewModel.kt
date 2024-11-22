package com.example.walmartapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.walmartapp.data.CountriesRepository
import com.example.walmartapp.data.local.models.Country
import com.example.walmartapp.di.ManualDIModule
import com.example.walmartapp.utils.Result
import com.example.walmartapp.utils.toCountry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _countriesError = MutableLiveData<String>()
    val countriesError: LiveData<String> = _countriesError

    init {
        getCountries()
    }
    private fun getCountries(scope: CoroutineScope = viewModelScope) {
        Log.d("MKDKDL", "getCountries")

        scope.launch {
            when (val result = repository.getCountries()) {
                is Result.Success -> {
                    _countries.value = result.data.body()?.map { it.toCountry() }
                }

                is Result.Error -> {
                    _countriesError.value = result.message
                }
            }
        }
    }

    class CountriesViewModelFactory(
        private val repository: CountriesRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CountriesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}