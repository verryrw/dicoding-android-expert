package com.verryrw.carapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.verryrw.carapp.core.domain.usecase.CarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    carUseCase: CarUseCase
) : ViewModel() {

    val favoriteCars = carUseCase.getFavoriteCar().asLiveData()
}