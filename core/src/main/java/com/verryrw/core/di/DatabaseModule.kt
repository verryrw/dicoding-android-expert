package com.verryrw.core.di

import android.content.Context
import androidx.room.Room
import com.verryrw.core.data.source.local.room.CarDao
import com.verryrw.core.data.source.local.room.CarDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideCarDatabase(@ApplicationContext context: Context): CarDatabase {
        return Room.databaseBuilder(
            context,
            CarDatabase::class.java,
            "Cars.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCarDao(carDatabase: CarDatabase): CarDao {
        return carDatabase.carDao()
    }
}