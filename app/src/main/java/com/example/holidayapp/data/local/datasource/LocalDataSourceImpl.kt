package com.example.holidayapp.data.local.datasource

import com.example.holidayapp.core.mapper.toFavoriteHolidayListingEntity
import com.example.holidayapp.data.local.dao.FavoriteHolidayDao
import com.example.holidayapp.data.local.entity.FavoriteHolidayListingEntity
import com.example.holidayapp.domain.model.HolidayDetail
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val favoriteHolidayDao: FavoriteHolidayDao
): LocalDataSource {
    override suspend fun deleteFavoriteById(favoriteId: Int) =
        favoriteHolidayDao.deleteFavoriteById(favoriteId)

    override suspend fun getAllFavoritesFromDb(): List<FavoriteHolidayListingEntity> =
        favoriteHolidayDao.getAllFavorites()

    override suspend fun insertFavoriteHolidayToDb(holiday: HolidayDetail) =
        favoriteHolidayDao.insertFavoriteHoliday(holiday.toFavoriteHolidayListingEntity())
}