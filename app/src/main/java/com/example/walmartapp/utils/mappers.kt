package com.example.walmartapp.utils

import CountryResponse
import com.example.walmartapp.data.local.models.Country

fun CountryResponse.toCountry() = Country(
    name = this.name ?: "Unknown country",
    capital = this.capital ?: "Unknown capital",
    code = this.code ?: "Unknown code",
    region = this.region ?: "Unknown region"
)