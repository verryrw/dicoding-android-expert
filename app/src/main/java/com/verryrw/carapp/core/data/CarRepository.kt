package com.verryrw.carapp.core.data

import com.verryrw.carapp.core.common.Resource
import com.verryrw.carapp.core.data.source.local.LocalDataSource
import com.verryrw.carapp.core.data.source.local.entity.CarEntity
import com.verryrw.carapp.core.data.source.remote.RemoteDataSource
import com.verryrw.carapp.core.data.source.remote.network.ApiResponse
import com.verryrw.carapp.core.data.source.remote.response.CarResponse
import com.verryrw.carapp.core.domain.models.Car
import com.verryrw.carapp.core.domain.repository.ICarRepository
import com.verryrw.carapp.core.utils.AppExecutors
import com.verryrw.carapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : ICarRepository {

    override fun getAllCars(): Flow<Resource<List<Car>>> =
        object : NetworkBoundResource<List<Car>, List<CarResponse>>() {
            override fun loadFromDB(): Flow<List<Car>> {
                val data = localDataSource.getAllCar().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
                return data
            }

            override fun shouldFetch(data: List<Car>?): Boolean = data.isNullOrEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<CarResponse>>> =
                remoteDataSource.getAllCars()

            override suspend fun saveCallResult(data: List<CarResponse>) {
                val carList: List<CarEntity> = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertCars(carList)
            }
        }.asFlow()

    override fun getFavoriteCar(): Flow<List<Car>> {
        return localDataSource.getFavoriteCar().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteCar(car: Car, state: Boolean) {
        val carEntity = DataMapper.mapDomainToEntity(car)
        appExecutors.diskIO().execute { localDataSource.setFavoriteCar(carEntity, state) }
    }
}