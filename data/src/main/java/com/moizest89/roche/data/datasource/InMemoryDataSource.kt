package com.moizest89.roche.data.datasource

import com.moizest89.roche.domain.model.BloodGlucoseEntry
import javax.inject.Inject

class InMemoryDataSource @Inject constructor(){
  private val entries = mutableListOf<BloodGlucoseEntry>()

  fun addEntry(entry: BloodGlucoseEntry) {
    entries.add(entry)
  }

  fun getEntries(): List<BloodGlucoseEntry> {
    return entries.toList()
  }
}