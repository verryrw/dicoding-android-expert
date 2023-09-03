package com.verryrw.carapp.core.data.source.remote.network

import com.verryrw.carapp.core.data.source.remote.response.CarResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("cars")
    suspend fun getAllCars(
        @Header("Authorization") token: String,
        @Header("apiKey") apiKey: String,
    ): List<CarResponse>
}