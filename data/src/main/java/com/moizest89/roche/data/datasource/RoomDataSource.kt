package com.moizest89.roche.data.datasource

import com.moizest89.roche.data.dao.BloodGlucoseDao
import com.moizest89.roche.data.model.BloodGlucoseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomDataSource @Inject constructor(
  private val bloodGlucoseDao: BloodGlucoseDao
) {

  suspend fun addEntry(entry: BloodGlucoseEntity) {
    bloodGlucoseDao.addEntry(entry)
  }

  fun getEntries(): Flow<List<BloodGlucoseEntity>> {
    return bloodGlucoseDao.getEntries()
  }
}