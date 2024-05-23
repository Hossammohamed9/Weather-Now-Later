package com.data.database

import android.content.Context
import androidx.room.Room
import com.data.database.dao.WeatherDAO
import com.core.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
internal object LocalModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(appDatabase: AppDatabase): WeatherDAO {
        return appDatabase.weatherDao()
    }

}