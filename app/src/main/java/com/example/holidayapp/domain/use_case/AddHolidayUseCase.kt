package com.example.holidayapp.domain.use_case

import com.example.holidayapp.data.repository.Repository
import com.example.holidayapp.domain.model.HolidayDetail
import javax.inject.Inject

class AddHolidayUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun addFavorite(holiday:HolidayDetail){
        repository.insertFavoriteShowToDb(holiday)
    }
}