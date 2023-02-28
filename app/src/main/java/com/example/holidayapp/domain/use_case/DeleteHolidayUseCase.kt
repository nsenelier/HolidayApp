package com.example.holidayapp.domain.use_case

import com.example.holidayapp.data.repository.Repository
import javax.inject.Inject

class DeleteHolidayUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun deleteFavorite(id: Int) {
        repository.deleteFavoriteById(id)
    }
}