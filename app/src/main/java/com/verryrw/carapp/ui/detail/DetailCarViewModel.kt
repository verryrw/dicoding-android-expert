package com.verryrw.carapp.ui.detail

import androidx.lifecycle.ViewModel
import com.verryrw.core.domain.models.Car
import com.verryrw.core.domain.usecase.CarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCarViewModel @Inject constructor(
    private val carUseCase: CarUseCase
) : ViewModel() {

    fun setFavoriteTourism(car: Car, newStatus: Boolean) =
        carUseCase.setFavoriteCar(car, newStatus)
}