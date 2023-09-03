package com.verryrw.carapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.verryrw.carapp.core.domain.usecase.CarUseCase

class FavoriteCarViewModel(
    carUseCase: CarUseCase
) : ViewModel() {

    val favoriteCars = carUseCase.getFavoriteCar().asLiveData()
}