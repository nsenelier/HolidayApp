package com.example.holidayapp.di

import android.app.Application
import androidx.room.Room
import com.example.holidayapp.data.local.dao.FavoriteHolidayDao
import com.example.holidayapp.data.local.datasource.LocalDataSource
import com.example.holidayapp.data.local.datasource.LocalDataSourceImpl
import com.example.holidayapp.data.local.db.HolidayDatabase
import com.example.holidayapp.data.remote.apiservice.HolidayApi
import com.example.holidayapp.data.remote.datasource.RemoteDataSource
import com.example.holidayapp.data.remote.datasource.RemoteDataSourceImpl
import com.example.holidayapp.data.repository.Repository
import com.example.holidayapp.data.repository.RepositoryImpl
import com.example.holidayapp.domain.use_case.AddHolidayUseCase
import com.example.holidayapp.domain.use_case.DeleteHolidayUseCase
import com.example.holidayapp.domain.use_case.GetHolidayUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)

class AppModule {
    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    @Provides
    fun providesRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(HolidayApi.BASE_URL)
        .build()

    @Provides
    fun providesHolidayApi(retrofit: Retrofit): HolidayApi = retrofit.create(HolidayApi::class.java)

    @Provides
    fun providesRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): Repository {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideAddHolidayUseCase(repository: Repository): AddHolidayUseCase {
        return AddHolidayUseCase(repository)
    }

    @Provides
    fun provideDeleteHolidayUseCase(repository: Repository): DeleteHolidayUseCase {
        return DeleteHolidayUseCase(repository)
    }

   @Provides
    fun provideGetHolidayUseCase(repository: Repository): GetHolidayUseCase {
        return GetHolidayUseCase(repository)
    }

    @Provides
    fun providesDatabase(app: Application): HolidayDatabase {
        return Room.databaseBuilder(
            app,
            HolidayDatabase::class.java,
            "holidaydb.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideHolidayDao(database: HolidayDatabase) = database.favoriteHolidayDao()

    @Provides
    fun getRemoteDS(holidayApi: HolidayApi): RemoteDataSource {
        return RemoteDataSourceImpl(holidayApi)
    }
    @Provides
    fun getLocalDS(favoriteDao: FavoriteHolidayDao
    ): LocalDataSource {
        return LocalDataSourceImpl(favoriteDao)
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}