package com.moizest89.roche.data.model

import com.moizest89.roche.domain.model.BloodGlucoseModel
import com.moizest89.roche.domain.model.BloodGlucoseUnit

fun BloodGlucoseEntity.toBloodGlucoseModel(): BloodGlucoseModel {
  return BloodGlucoseModel(
    value = this.value.toDouble(),
    unit = BloodGlucoseUnit.valueOf(this.unit)
  )
}

fun BloodGlucoseModel.toBloodGlucoseEntity(): BloodGlucoseEntity {
  return BloodGlucoseEntity(
    value = this.value.toFloat(),
    unit = this.unit.name,
    timestamp = System.currentTimeMillis()
  )
}