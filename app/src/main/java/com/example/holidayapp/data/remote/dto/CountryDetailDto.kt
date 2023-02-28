package com.example.holidayapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryDetailDto (
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("name")
    val name: String
    )