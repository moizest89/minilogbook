package com.moizest89.roche.domain.usecase

import com.moizest89.roche.domain.model.BloodGlucoseUnit
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAverageBloodGlucoseUseCase @Inject constructor(
  private val repository: BloodGlucoseRepository,
) : (BloodGlucoseUnit) -> Flow<Double> {
  override fun invoke(unit: BloodGlucoseUnit): Flow<Double> {
    return repository.getEntries().map { entries ->
      val convertedValues = entries.map {
        if (it.unit == unit) it.value else convertValue(it.value, it.unit, unit)
      }
      if (convertedValues.isNotEmpty()) convertedValues.average() else 0.0
    }
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