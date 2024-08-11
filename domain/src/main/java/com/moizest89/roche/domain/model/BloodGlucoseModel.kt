package com.moizest89.roche.domain.model

data class BloodGlucoseModel(
  val value: Double,
  val unit: BloodGlucoseUnit,
  val timestamp: Long = System.currentTimeMillis(),
)

enum class BloodGlucoseUnit(val prefix: String) {
  MMOL_L("mmol/L"),
  MG_DL("mg/dL")
}