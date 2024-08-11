package com.moizest89.roche.domain.DI

import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import com.moizest89.roche.domain.usecase.AddBloodGlucoseEntryUseCase
import com.moizest89.roche.domain.usecase.GetAverageBloodGlucoseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

  @Provides
  @Singleton
  fun provideAddBloodGlucoseEntryUseCase(
    repository: BloodGlucoseRepository,
  ): AddBloodGlucoseEntryUseCase {
    return AddBloodGlucoseEntryUseCase(repository)
  }

  @Provides
  @Singleton
  fun provideGetAverageBloodGlucoseUseCase(
    repository: BloodGlucoseRepository
  ): GetAverageBloodGlucoseUseCase {
    return GetAverageBloodGlucoseUseCase(repository)
  }
}