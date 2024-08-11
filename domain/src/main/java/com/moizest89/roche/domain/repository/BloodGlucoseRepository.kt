package com.moizest89.roche.domain.repository

import com.moizest89.roche.domain.model.BloodGlucoseModel
import kotlinx.coroutines.flow.Flow

interface BloodGlucoseRepository {
  suspend fun addEntry(entry: BloodGlucoseModel)
  fun getEntries(): Flow<List<BloodGlucoseModel>>
}