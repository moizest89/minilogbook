package com.moizest89.roche.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moizest89.roche.data.model.BloodGlucoseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodGlucoseDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addEntry(entry: BloodGlucoseEntity)

  @Query("SELECT * FROM BloodGlucoseEntity ORDER BY timestamp DESC")
  fun getEntries(): Flow<List<BloodGlucoseEntity>>

  @Query("DELETE FROM BloodGlucoseEntity")
  fun deleteAllEntries()
}