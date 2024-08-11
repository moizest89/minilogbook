package com.moizest89.roche.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moizest89.roche.data.model.BloodGlucoseEntity

@Database(entities = [BloodGlucoseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun bloodGlucoseDao(): BloodGlucoseDao
}