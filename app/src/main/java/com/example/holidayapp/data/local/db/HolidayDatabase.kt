package com.example.holidayapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.holidayapp.data.local.dao.FavoriteHolidayDao
import com.example.holidayapp.data.local.entity.FavoriteHolidayListingEntity

@Database(
    entities = [FavoriteHolidayListingEntity::class],
    version = 12,
    exportSchema = false
)

@TypeConverters
abstract class HolidayDatabase: RoomDatabase() {
    abstract fun favoriteHolidayDao(): FavoriteHolidayDao
}