package com.example.walmartapp.presentation.viewModel

import CountryResponse
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.walmartapp.MainDispatcherRule
import com.example.walmartapp.data.CountriesRepository
import com.example.walmartapp.data.local.models.Country
import com.example.walmartapp.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class CountriesViewModelTest {

    private lateinit var viewModel: CountriesViewModel
    private val mockRepository: CountriesRepository = mockk()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CountriesViewModel(mockRepository)
    }

    @Test
    fun `Given a successful response, when viewmodel getCountries is called, it sets the countries field with data`() = runTest {
        val testCountry1 = Country(name = "Ukraine", region = "EU", capital = "Kiev", code = "uk")
        val expectedResult = listOf(testCountry1)
        val countryResponse = listOf(
            CountryResponse(
                name = testCountry1.name,
                region = testCountry1.region,
                capital = testCountry1.capital,
                code = testCountry1.code
            )
        )

        // Mock repository behavior
        coEvery { mockRepository.getCountries() } returns Result.Success(
            Response.success(
                countryResponse
            )
        )

        // Call the ViewModel method
        viewModel.getCountries()

        // Verify repository call and state changes
        coVerify { mockRepository.getCountries() }
        assert(viewModel.countries.value == expectedResult)
    }

    @Test
    fun `Given a failure response, when viewmodel getCountries is called, it sets the countries error field`() {
        val expectedResult = "Error: Spaghetti code found"

        // Mock repository behavior
        coEvery { mockRepository.getCountries() } returns Result.Error(
            message = expectedResult,
            throwable = Throwable()
        )

        // Call the ViewModel method
        viewModel.getCountries()

        // Verify repository call and state changes
        coVerify { mockRepository.getCountries() }
        assert(viewModel.countriesError.value == expectedResult)
    }
}