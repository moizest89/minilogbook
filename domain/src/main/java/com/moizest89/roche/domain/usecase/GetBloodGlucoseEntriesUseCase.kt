package com.moizest89.roche.domain.usecase

import com.moizest89.roche.domain.model.BloodGlucoseEntry
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import javax.inject.Inject

class GetBloodGlucoseEntriesUseCase @Inject constructor(
  private val repository: BloodGlucoseRepository,
) : () -> List<BloodGlucoseEntry>{
  override operator fun invoke(): List<BloodGlucoseEntry> = repository.getEntries()
}