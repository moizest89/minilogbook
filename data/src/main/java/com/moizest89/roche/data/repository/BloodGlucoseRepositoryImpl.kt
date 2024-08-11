package com.moizest89.roche.data.repository

import com.moizest89.roche.data.datasource.RoomDataSource
import com.moizest89.roche.data.model.toBloodGlucoseEntity
import com.moizest89.roche.data.model.toBloodGlucoseModel
import com.moizest89.roche.domain.model.BloodGlucoseModel
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BloodGlucoseRepositoryImpl @Inject constructor(
  private val dataSource: RoomDataSource
) : BloodGlucoseRepository {
  override suspend fun addEntry(entry: BloodGlucoseModel) =
    dataSource.addEntry(entry.toBloodGlucoseEntity())

  override fun getEntries(): Flow<List<BloodGlucoseModel>> {
    return dataSource.getEntries().map { entityList ->
      entityList.map { entity ->
        entity.toBloodGlucoseModel()
      }
    }
  }
}