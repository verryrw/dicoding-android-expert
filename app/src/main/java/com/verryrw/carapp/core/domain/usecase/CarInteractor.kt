package com.verryrw.carapp.core.domain.usecase

import com.verryrw.carapp.core.domain.models.Car
import com.verryrw.carapp.core.domain.repository.ICarRepository
import javax.inject.Inject

class CarInteractor @Inject constructor(
    private val carRepository: ICarRepository
) : CarUseCase {

    override fun getAllCars() = carRepository.getAllCars()

    override fun getFavoriteCar() = carRepository.getFavoriteCar()

    override fun setFavoriteCar(car: Car, state: Boolean) = carRepository.setFavoriteCar(car, state)
}