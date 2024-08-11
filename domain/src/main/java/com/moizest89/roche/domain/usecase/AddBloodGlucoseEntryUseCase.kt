package com.moizest89.roche.domain.usecase

import com.moizest89.roche.domain.model.BloodGlucoseModel
import com.moizest89.roche.domain.model.BloodGlucoseUnit
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import javax.inject.Inject

class AddBloodGlucoseEntryUseCase @Inject constructor(
  private val repository: BloodGlucoseRepository
) : suspend (Double, BloodGlucoseUnit) -> Unit {
  override suspend fun invoke(value: Double, unit: BloodGlucoseUnit) {
    val entry = BloodGlucoseModel(value, unit)
    repository.addEntry(entry)
  }
}