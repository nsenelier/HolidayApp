package com.example.holidayapp.domain.model

import java.util.Date

data class HolidayDetail(
    val id: Int = 0,
    val date: String,
    val localName: String,
    val name: String,
    val countryCode: String,
    var isFavorite: Boolean = false
)

//"date": "2023-12-26",
//"localName": "Stefanitag",
//"name": "St. Stephen's Day",
//"countryCode": "AT",
//"fixed": true,
//"global": true,
//"counties": null,
//"launchYear": null,
//"types": [
//"Public"
//]
