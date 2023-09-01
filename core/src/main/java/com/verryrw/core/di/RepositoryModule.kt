package com.verryrw.core.di

import com.verryrw.core.data.CarRepository
import com.verryrw.core.domain.repository.ICarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(carRepository: CarRepository): ICarRepository
}