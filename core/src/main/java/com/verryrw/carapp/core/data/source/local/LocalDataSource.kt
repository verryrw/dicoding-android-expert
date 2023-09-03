package com.verryrw.carapp.core.data.source.local

import com.verryrw.carapp.core.data.source.local.entity.CarEntity
import com.verryrw.carapp.core.data.source.local.room.CarDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val carDao: CarDao
) {

    fun getAllCar(): Flow<List<CarEntity>> = carDao.getAllCars()

    fun getFavoriteCar(): Flow<List<CarEntity>> = carDao.getFavoriteCars()

    suspend fun insertCars(cars: List<CarEntity>) {
        carDao.insertCars(cars)
    }

    fun setFavoriteCar(
        car: CarEntity,
        newState: Boolean
    ) {
        val newCar = car.copy(isFavorite = newState)
        carDao.updateFavoriteCar(newCar)
    }
}