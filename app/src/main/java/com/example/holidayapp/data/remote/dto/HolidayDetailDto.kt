package com.example.holidayapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class HolidayDetailDto(
    @SerializedName("counties")
    val counties: Any,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("fixed")
    val fixed: Boolean,
    @SerializedName("global")
    val global: Boolean,
    @SerializedName("launchYear")
    val launchYear: Any,
    @SerializedName("localName")
    val localName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("types")
    val types: List<String>
)