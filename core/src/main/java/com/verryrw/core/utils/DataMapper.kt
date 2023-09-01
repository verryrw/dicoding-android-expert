package com.verryrw.core.utils

import com.verryrw.core.data.source.local.entity.CarEntity
import com.verryrw.core.data.source.remote.response.CarResponse
import com.verryrw.core.domain.models.Car

object DataMapper {
    fun mapResponsesToEntities(input: List<CarResponse>): List<CarEntity> {
        val carList = ArrayList<CarEntity>()
        input.map {
            val car = CarEntity(
                id = it.id,
                name = it.title,
                description = it.description,
                urlToImage = it.urlToImage,
                isFavorite = false,
            )
            carList.add(car)
        }
        return carList
    }

    fun mapEntitiesToDomain(input: List<CarEntity>): List<Car> =
        input.map {
            Car(
                id = it.id,
                description = it.description,
                name = it.name,
                urlToImage = it.urlToImage,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Car) = CarEntity(
        id = input.id,
        description = input.description,
        name = input.name,
        urlToImage = input.urlToImage,
        isFavorite = input.isFavorite
    )
}