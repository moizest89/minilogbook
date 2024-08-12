package com.moizest89.roche.domain.model

data class BloodGlucoseModel(
  val value: Double,
  val unit: BloodGlucoseUnit,
  val timestamp: Long,
){
  constructor(value: Double, unit: BloodGlucoseUnit) : this(value, unit, System.currentTimeMillis())
}


enum class BloodGlucoseUnit(val prefix: String) {
  MMOL_L("mmol/L"),
  MG_DL("mg/dL")
}