package com.example.holidayapp.domain.use_case

import com.example.holidayapp.data.remote.dto.HolidayDto
import com.example.holidayapp.data.repository.Repository
import com.example.holidayapp.domain.model.HolidayDetail
import com.example.holidayapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHolidayUseCase @Inject constructor(
    private val repository: Repository
){
    suspend fun getFavorites(): Flow<Resource<List<HolidayDetail>>> {
        return  repository.getFavorites()
    }
    suspend fun getHoliday(query: String): HolidayDto {
        return repository.getHolidayListingFromApi(query)
    }
}