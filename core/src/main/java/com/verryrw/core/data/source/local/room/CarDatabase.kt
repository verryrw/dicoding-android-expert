package com.verryrw.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.verryrw.core.data.source.local.entity.CarEntity

@Database(entities = [CarEntity::class], version = 1, exportSchema = false)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}
