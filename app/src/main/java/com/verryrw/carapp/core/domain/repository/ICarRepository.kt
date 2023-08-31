package com.verryrw.carapp.core.domain.repository

import com.verryrw.carapp.core.common.Resource
import com.verryrw.carapp.core.domain.models.Car
import kotlinx.coroutines.flow.Flow

interface ICarRepository {

    fun getAllCars(): Flow<Resource<List<Car>>>

    fun getFavoriteCar(): Flow<List<Car>>

    fun setFavoriteCar(car: Car, state: Boolean)
}