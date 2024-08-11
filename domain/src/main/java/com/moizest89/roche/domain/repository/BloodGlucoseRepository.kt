package com.moizest89.roche.domain.repository

import com.moizest89.roche.domain.model.BloodGlucoseEntry

interface BloodGlucoseRepository {
  fun addEntry(entry: BloodGlucoseEntry)
  fun getEntries(): List<BloodGlucoseEntry>
}