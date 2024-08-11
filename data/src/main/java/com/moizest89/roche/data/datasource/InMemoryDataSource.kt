package com.moizest89.roche.data.datasource

import com.moizest89.roche.domain.model.BloodGlucoseModel
import javax.inject.Inject

class InMemoryDataSource @Inject constructor(){
  private val entries = mutableListOf<BloodGlucoseModel>()

  fun addEntry(entry: BloodGlucoseModel) {
    entries.add(entry)
  }

  fun getEntries(): List<BloodGlucoseModel> {
    return entries.toList()
  }
}