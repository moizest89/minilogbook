package com.moizest89.roche.data.DI

import com.moizest89.roche.data.repository.BloodGlucoseRepositoryImpl
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

  @Binds
  @Singleton
  abstract fun bindBloodGlucoseRepository(
    impl: BloodGlucoseRepositoryImpl
  ): BloodGlucoseRepository

}