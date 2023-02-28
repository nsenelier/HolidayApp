package com.example.holidayapp.data.remote.datasource

import com.example.holidayapp.data.remote.dto.CountryDto
import com.example.holidayapp.data.remote.dto.HolidayDto

interface RemoteDataSource {
    suspend fun getHolidayListFromApi(code: String): HolidayDto
    suspend fun getCountryListFromApi(): CountryDto
}