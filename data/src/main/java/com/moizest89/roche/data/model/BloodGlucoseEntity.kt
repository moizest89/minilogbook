package com.moizest89.roche.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BloodGlucoseEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  @ColumnInfo(name = "value") val value: Float,
  @ColumnInfo(name = "unit") val unit: String, // Could be "mmol/L" or "mg/dL"
  @ColumnInfo(name = "timestamp") val timestamp: Long,
)
