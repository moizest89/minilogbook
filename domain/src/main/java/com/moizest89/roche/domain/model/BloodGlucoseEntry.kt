package com.moizest89.roche.domain.model

data class BloodGlucoseEntry(
  val value: Double,
  val unit: BloodGlucoseUnit
)

enum class BloodGlucoseUnit(val prefix: String) {
  MMOL_L("mmol/L"),
  MG_DL("mg/dL")
}