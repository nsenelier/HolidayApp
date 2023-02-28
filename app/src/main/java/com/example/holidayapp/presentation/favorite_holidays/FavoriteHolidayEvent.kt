package com.example.holidayapp.presentation.favorite_holidays

sealed class FavoriteHolidayEvent {
    data class OnDeleteSelected(val id: Int) : FavoriteHolidayEvent()
    object LoadFavoriteHoliday : FavoriteHolidayEvent()
}