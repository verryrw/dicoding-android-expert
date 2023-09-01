package com.verryrw.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val urlToImage: String,
    var isFavorite: Boolean = false
)