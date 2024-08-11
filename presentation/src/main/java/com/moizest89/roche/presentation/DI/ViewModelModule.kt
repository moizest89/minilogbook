package com.moizest89.roche.presentation.DI

import com.moizest89.roche.domain.usecase.AddBloodGlucoseEntryUseCase
import com.moizest89.roche.domain.usecase.GetAverageBloodGlucoseUseCase
import com.moizest89.roche.domain.usecase.GetBloodGlucoseEntriesUseCase
import com.moizest89.roche.presentation.logbook.LogbookViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

  @Provides
  fun provideBloodGlucoseViewModel(
    addBloodGlucoseEntryUseCase: AddBloodGlucoseEntryUseCase,
    getBloodGlucoseEntriesUseCase : GetBloodGlucoseEntriesUseCase,
    getAverageBloodGlucoseUseCase: GetAverageBloodGlucoseUseCase
  ): LogbookViewModel {
    return LogbookViewModel(
      addBloodGlucoseEntryUseCase,
      getBloodGlucoseEntriesUseCase,
      getAverageBloodGlucoseUseCase,
    )
  }
}