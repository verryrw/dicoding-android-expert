package com.verryrw.carapp.core.domain.usecase

import com.verryrw.carapp.core.common.Resource
import com.verryrw.carapp.core.domain.models.Car
import kotlinx.coroutines.flow.Flow

interface CarUseCase {
    fun getAllCars(): Flow<Resource<List<Car>>>
    fun getFavoriteCar(): Flow<List<Car>>
    fun setFavoriteCar(car: Car, state: Boolean)
}