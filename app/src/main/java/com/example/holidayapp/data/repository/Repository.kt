package com.example.holidayapp.data.repository

import com.example.holidayapp.data.local.entity.FavoriteHolidayListingEntity
import com.example.holidayapp.data.remote.dto.CountryDto
import com.example.holidayapp.data.remote.dto.HolidayDto
import com.example.holidayapp.domain.model.HolidayDetail
import com.example.holidayapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCountryListingFromApi(): CountryDto

    suspend fun getHolidayListingFromApi(code: String): HolidayDto

    suspend fun insertFavoriteShowToDb(holiday: HolidayDetail)

    suspend fun getFavorites(): Flow<Resource<List<HolidayDetail>>>

    suspend fun deleteFavoriteById(favoriteId: Int)

    suspend fun getAllFavoritesFromDb(): List<FavoriteHolidayListingEntity>
}