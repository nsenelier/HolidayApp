package com.example.holidayapp.data.local.datasource

import com.example.holidayapp.data.local.entity.FavoriteHolidayListingEntity
import com.example.holidayapp.domain.model.HolidayDetail

interface LocalDataSource {
    suspend fun deleteFavoriteById(favoriteId: Int)

    suspend fun getAllFavoritesFromDb(): List<FavoriteHolidayListingEntity>

    suspend fun insertFavoriteHolidayToDb(holiday: HolidayDetail)

}