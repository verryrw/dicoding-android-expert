package com.verryrw.core.data.source.remote

import android.util.Log
import com.verryrw.core.common.Constants.API_KEY
import com.verryrw.core.common.Constants.TOKEN
import com.verryrw.core.data.source.remote.network.ApiResponse
import com.verryrw.core.data.source.remote.network.ApiService
import com.verryrw.core.data.source.remote.response.CarResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllCars(): Flow<ApiResponse<List<CarResponse>>> {
        return flow {
            try {
                val response = apiService.getAllCars(TOKEN, API_KEY)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}