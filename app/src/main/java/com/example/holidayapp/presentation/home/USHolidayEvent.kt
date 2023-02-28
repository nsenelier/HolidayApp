package com.example.holidayapp.presentation.home

import com.example.holidayapp.domain.model.HolidayDetail

sealed class USHolidayEvent {
    object LoadHoliday : USHolidayEvent()
    data class OnFavoriteSelected(val holiday: HolidayDetail): USHolidayEvent()
    data class DeleteFavorite(val id: Int) : USHolidayEvent()
}