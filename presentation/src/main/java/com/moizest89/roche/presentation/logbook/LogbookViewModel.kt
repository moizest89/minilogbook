package com.moizest89.roche.presentation.logbook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moizest89.roche.domain.model.BloodGlucoseEntry
import com.moizest89.roche.domain.model.BloodGlucoseUnit
import com.moizest89.roche.domain.usecase.AddBloodGlucoseEntryUseCase
import com.moizest89.roche.domain.usecase.GetAverageBloodGlucoseUseCase
import com.moizest89.roche.domain.usecase.GetBloodGlucoseEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogbookViewModel @Inject constructor(
  private val addEntryUseCase: AddBloodGlucoseEntryUseCase,
  private val getBloodGlucoseEntriesUseCase : GetBloodGlucoseEntriesUseCase,
  private val getAverageUseCase: GetAverageBloodGlucoseUseCase
) : ViewModel() {

  val averageBloodGlucose = MutableLiveData<Double>()
  val entries = MutableLiveData<List<BloodGlucoseEntry>>()

  fun addBloodGlucoseEntry(value: Double, unit: BloodGlucoseUnit) {
    addEntryUseCase.execute(value, unit)
    updateEntries()
    updateAverage(unit)
  }

  private fun updateEntries() {
    // Assuming repository can provide entries directly (in real scenarios, this might be through a UseCase)
    entries.value = getBloodGlucoseEntriesUseCase()
  }

  fun updateAverage(unit: BloodGlucoseUnit) {
    averageBloodGlucose.value = getAverageUseCase.execute(unit)
  }
}