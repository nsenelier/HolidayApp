package com.example.holidayapp.presentation.favorite_holidays

import com.example.holidayapp.domain.model.HolidayDetail

data class FavoriteHolidayUiState(
    val favoriteHoliday: List<HolidayDetail> = emptyList(),
    val id: Int=0,
    val isLoading:Boolean= false,
    val isRefreshing:Boolean=false,
    val error:String=""
)