package com.verryrw.carapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CarResponse(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)