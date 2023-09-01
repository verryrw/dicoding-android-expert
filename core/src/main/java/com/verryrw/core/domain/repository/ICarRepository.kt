package com.verryrw.core.domain.repository

import com.verryrw.core.common.Resource
import com.verryrw.core.domain.models.Car
import kotlinx.coroutines.flow.Flow

interface ICarRepository {

    fun getAllCars(): Flow<Resource<List<Car>>>

    fun getFavoriteCar(): Flow<List<Car>>

    fun setFavoriteCar(car: Car, state: Boolean)
}