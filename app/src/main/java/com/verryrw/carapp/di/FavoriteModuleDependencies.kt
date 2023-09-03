package com.verryrw.carapp.di

import com.verryrw.carapp.core.domain.usecase.CarUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun carUseCase(): CarUseCase
}
