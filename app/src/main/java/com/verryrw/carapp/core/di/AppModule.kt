package com.verryrw.carapp.core.di

import com.verryrw.carapp.core.domain.use_case.CarInteractor
import com.verryrw.carapp.core.domain.use_case.CarUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideCarUseCase(carInteractor: CarInteractor): CarUseCase
}