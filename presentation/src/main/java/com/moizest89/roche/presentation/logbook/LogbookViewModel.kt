package com.moizest89.roche.presentation.logbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moizest89.roche.domain.model.BloodGlucoseModel
import com.moizest89.roche.domain.model.BloodGlucoseUnit
import com.moizest89.roche.domain.usecase.AddBloodGlucoseEntryUseCase
import com.moizest89.roche.domain.usecase.DeleteBloodGlucoseEntriesUseCase
import com.moizest89.roche.domain.usecase.GetAverageBloodGlucoseUseCase
import com.moizest89.roche.domain.usecase.GetBloodGlucoseEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LogbookViewModel @Inject constructor(
  private val addEntryUseCase: AddBloodGlucoseEntryUseCase,
  private val getBloodGlucoseEntriesUseCase: GetBloodGlucoseEntriesUseCase,
  private val getAverageUseCase: GetAverageBloodGlucoseUseCase,
  private val deleteBloodGlucoseEntriesUseCase: DeleteBloodGlucoseEntriesUseCase,
) : ViewModel() {

  private val _averageBloodGlucose = MutableStateFlow(0.0)
  val averageBloodGlucose: StateFlow<Double> = _averageBloodGlucose.asStateFlow()

  private val _bloodGlucoseEntries = MutableStateFlow<List<BloodGlucoseModel>>(emptyList())
  val bloodGlucoseEntries: StateFlow<List<BloodGlucoseModel>> = _bloodGlucoseEntries.asStateFlow()

  init {
    viewModelScope.launch {
      getBloodGlucoseEntriesUseCase()
        .collect { entries ->
          _bloodGlucoseEntries.value = entries
        }
    }
  }

  fun addBloodGlucoseEntry(value: Double, unit: BloodGlucoseUnit) {
    viewModelScope.launch {
      addEntryUseCase.invoke(value, unit)
      updateAverage(unit)
    }
  }

  fun updateAverage(unit: BloodGlucoseUnit) {
    viewModelScope.launch {
      getAverageUseCase.invoke(unit).collect {
        _averageBloodGlucose.value = it
      }
    }
  }

  fun deleteAllEntries() {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        deleteBloodGlucoseEntriesUseCase()
      }
    }
  }
}