package com.verryrw.core.data

import com.verryrw.core.data.source.local.LocalDataSource
import com.verryrw.core.data.source.local.entity.CarEntity
import com.verryrw.core.data.source.remote.RemoteDataSource
import com.verryrw.core.data.source.remote.network.ApiResponse
import com.verryrw.core.data.source.remote.response.CarResponse
import com.verryrw.core.domain.models.Car
import com.verryrw.core.domain.repository.ICarRepository
import com.verryrw.core.utils.AppExecutors
import com.verryrw.core.utils.DataMapper
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

    override fun getAllCars(): Flow<com.verryrw.core.common.Resource<List<Car>>> =
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