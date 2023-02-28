package com.example.holidayapp.data.repository

import com.example.holidayapp.core.mapper.toHolidayListing
import com.example.holidayapp.data.local.datasource.LocalDataSource
import com.example.holidayapp.data.local.entity.FavoriteHolidayListingEntity
import com.example.holidayapp.data.remote.datasource.RemoteDataSource
import com.example.holidayapp.data.remote.dto.CountryDto
import com.example.holidayapp.data.remote.dto.HolidayDto
import com.example.holidayapp.domain.model.HolidayDetail
import com.example.holidayapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): Repository {
    override suspend fun getCountryListingFromApi(): CountryDto =
        remoteDataSource.getCountryListFromApi()

    override suspend fun getHolidayListingFromApi(code: String): HolidayDto =
        remoteDataSource.getHolidayListFromApi(code)

    override suspend fun insertFavoriteShowToDb(holiday: HolidayDetail) =
      localDataSource.insertFavoriteHolidayToDb(holiday)

    override suspend fun getFavorites(): Flow<Resource<List<HolidayDetail>>> {
       return flow{
           emit(Resource.Loading(true))
           val localList = getAllFavoritesFromDb()
           emit(Resource.Success(
               data = localList.map { it.toHolidayListing() }
           ))

           val isDbEmpty = localList.isEmpty()
           val loadFromCache = !isDbEmpty

           if (loadFromCache) {
               emit(Resource.Loading(true))
               return@flow //we don't make request since we have data already on db and return flow
           } else {
               emit(Resource.Error("No  data"))
           }
       }
    }

    override suspend fun deleteFavoriteById(favoriteId: Int) =
        localDataSource.deleteFavoriteById(favoriteId)

    override suspend fun getAllFavoritesFromDb(): List<FavoriteHolidayListingEntity> =
        localDataSource.getAllFavoritesFromDb()
}