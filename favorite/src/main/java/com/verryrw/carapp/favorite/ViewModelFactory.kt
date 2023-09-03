package com.verryrw.carapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.verryrw.carapp.core.domain.usecase.CarUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val carUseCase: CarUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteCarViewModel::class.java) -> {
                FavoriteCarViewModel(carUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}