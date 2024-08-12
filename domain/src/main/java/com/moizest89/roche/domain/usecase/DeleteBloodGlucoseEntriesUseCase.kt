package com.moizest89.roche.domain.usecase

import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import javax.inject.Inject

class DeleteBloodGlucoseEntriesUseCase @Inject constructor(
  private val repository: BloodGlucoseRepository
) : suspend () -> Unit {
  override suspend fun invoke() {
    repository.deleteAllEntries()
  }
}