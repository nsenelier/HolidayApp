package com.example.holidayapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_holiday_listing_table")
data class FavoriteHolidayListingEntity (
    val date: String,
    val localName: String,
    val name: String,
    val countryCode: String,
    @PrimaryKey val id: Int? = null
)