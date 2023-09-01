package com.verryrw.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val id: Int,
    val name: String,
    val description: String,
    val urlToImage: String,
    val isFavorite: Boolean,
) : Parcelable