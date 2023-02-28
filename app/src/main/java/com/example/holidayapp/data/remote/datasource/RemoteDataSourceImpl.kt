package com.example.holidayapp.data.remote.datasource

import com.example.holidayapp.data.remote.apiservice.HolidayApi
import com.example.holidayapp.data.remote.dto.CountryDto
import com.example.holidayapp.data.remote.dto.HolidayDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: HolidayApi
): RemoteDataSource
{
    override suspend fun getHolidayListFromApi(code: String): HolidayDto = api.getHolidays(code)
    override suspend fun getCountryListFromApi(): CountryDto = api.getCountryCode()
}