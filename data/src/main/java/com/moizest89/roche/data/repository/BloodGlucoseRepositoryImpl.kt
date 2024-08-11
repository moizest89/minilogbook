package com.moizest89.roche.data.repository

import com.moizest89.roche.data.datasource.InMemoryDataSource
import com.moizest89.roche.domain.model.BloodGlucoseEntry
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import javax.inject.Inject

class BloodGlucoseRepositoryImpl @Inject constructor(
  private val dataSource: InMemoryDataSource
) : BloodGlucoseRepository {
  override fun addEntry(entry: BloodGlucoseEntry) {
    dataSource.addEntry(entry)
  }

  override fun getEntries(): List<BloodGlucoseEntry> {
    return dataSource.getEntries()
  }
}