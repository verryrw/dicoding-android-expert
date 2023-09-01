package com.verryrw.core.domain.usecase

import com.verryrw.core.common.Resource
import com.verryrw.core.domain.models.Car
import kotlinx.coroutines.flow.Flow

interface CarUseCase {
    fun getAllCars(): Flow<Resource<List<Car>>>
    fun getFavoriteCar(): Flow<List<Car>>
    fun setFavoriteCar(car: Car, state: Boolean)
}