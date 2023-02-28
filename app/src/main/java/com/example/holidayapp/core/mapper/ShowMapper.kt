package com.example.holidayapp.core.mapper

import com.example.holidayapp.data.local.entity.FavoriteHolidayListingEntity
import com.example.holidayapp.domain.model.HolidayDetail

fun HolidayDetail.toFavoriteHolidayListingEntity(): FavoriteHolidayListingEntity{
    return FavoriteHolidayListingEntity(
        id=id,
        name = name,
        localName = localName,
        date = date,
        countryCode = countryCode
    )
}
fun FavoriteHolidayListingEntity.toHolidayListing(): HolidayDetail{
    return HolidayDetail(
        id = id!!,
        name = name,
        localName = localName,
        date = date,
        countryCode = countryCode
    )
}

