package com.verryrw.carapp.di

import com.verryrw.carapp.core.domain.usecase.CarInteractor
import com.verryrw.carapp.core.domain.usecase.CarUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideCarUseCase(carInteractor: CarInteractor): CarUseCase
}