package com.example.holidayapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.holidayapp.data.local.entity.FavoriteHolidayListingEntity

@Dao
interface FavoriteHolidayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteHoliday(holiday: FavoriteHolidayListingEntity)

    @Query("SELECT * FROM favorite_holiday_listing_table")
    suspend fun getAllFavorites(): List<FavoriteHolidayListingEntity>

    @Query("DELETE FROM favorite_holiday_listing_table WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)

}