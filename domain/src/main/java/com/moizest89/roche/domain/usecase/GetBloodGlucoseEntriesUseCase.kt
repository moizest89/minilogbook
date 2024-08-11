package com.moizest89.roche.domain.usecase

import com.moizest89.roche.domain.model.BloodGlucoseModel
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBloodGlucoseEntriesUseCase @Inject constructor(
  private val repository: BloodGlucoseRepository,
) : () -> Flow<List<BloodGlucoseModel>> {
  override operator fun invoke(): Flow<List<BloodGlucoseModel>> = repository.getEntries()
}