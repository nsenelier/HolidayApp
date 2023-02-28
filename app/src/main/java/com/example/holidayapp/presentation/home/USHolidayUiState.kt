package com.example.holidayapp.presentation.home

import com.example.holidayapp.domain.model.HolidayDetail

data class USHolidayUiState(
    val holidays: List<HolidayDetail> = emptyList(),
    val holiday: HolidayDetail= HolidayDetail(0, "","","","", false),
    val id: Int=0,
    val isLoading:Boolean= false,
    val isRefreshing:Boolean=false,
    val error:String=""
)