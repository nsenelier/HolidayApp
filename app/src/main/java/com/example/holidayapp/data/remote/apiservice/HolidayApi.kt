package com.example.holidayapp.data.remote.apiservice

import com.example.holidayapp.data.remote.dto.CountryDto
import com.example.holidayapp.data.remote.dto.HolidayDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HolidayApi {

    @GET(ENDPOINT_PUBLIC)
    suspend fun getHolidays(
        @Query(PARAM_CODE) code: String
    ): HolidayDto

    @GET(COUNTRIES)
    suspend fun getCountryCode(): CountryDto

    companion object {
        const val BASE_URL = "https://date.nager.at/api/v3"
        const val PARAM_CODE= "countryCode"
        const val COUNTRIES = "api/v3/AvailableCountries"
        const val ENDPOINT_PUBLIC = "api/v3/PublicHolidays/2023"
    }

}