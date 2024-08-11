package com.moizest89.roche.domain.usecase

import com.moizest89.roche.domain.model.BloodGlucoseEntry
import com.moizest89.roche.domain.model.BloodGlucoseUnit
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import javax.inject.Inject

class AddBloodGlucoseEntryUseCase @Inject constructor(
  private val repository: BloodGlucoseRepository
) {
  fun execute(value: Double, unit: BloodGlucoseUnit) {
    val entry = BloodGlucoseEntry(value, unit)
    repository.addEntry(entry)
  }
}