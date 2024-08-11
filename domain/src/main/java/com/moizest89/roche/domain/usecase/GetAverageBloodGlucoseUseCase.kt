package com.moizest89.roche.domain.usecase

import com.moizest89.roche.domain.model.BloodGlucoseUnit
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import javax.inject.Inject

class GetAverageBloodGlucoseUseCase @Inject constructor(
  private val repository: BloodGlucoseRepository,
) {
  fun execute(unit: BloodGlucoseUnit): Double {
    val entries = repository.getEntries()
    // Convert values to the same unit and calculate the average
    val convertedValues = entries.map {
      if (it.unit == unit) it.value else convertValue(it.value, it.unit, unit)
    }
    return if (convertedValues.isNotEmpty()) convertedValues.average() else 0.0
  }

  private fun convertValue(
    value: Double,
    fromUnit: BloodGlucoseUnit,
    toUnit: BloodGlucoseUnit
  ): Double {
    return if (fromUnit == BloodGlucoseUnit.MMOL_L && toUnit == BloodGlucoseUnit.MG_DL) {
      value * 18.0182
    } else if (fromUnit == BloodGlucoseUnit.MG_DL && toUnit == BloodGlucoseUnit.MMOL_L) {
      value / 18.0182
    } else {
      value
    }
  }
}