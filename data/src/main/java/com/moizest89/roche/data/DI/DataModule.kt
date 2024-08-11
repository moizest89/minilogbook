package com.moizest89.roche.data.DI

import android.content.Context
import androidx.room.Room
import com.moizest89.roche.data.dao.AppDatabase
import com.moizest89.roche.data.dao.BloodGlucoseDao
import com.moizest89.roche.data.repository.BloodGlucoseRepositoryImpl
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

  companion object {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
      return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "blood_glucose_db"
      ).build()
    }

    @Provides
    fun provideBloodGlucoseDao(db: AppDatabase): BloodGlucoseDao {
      return db.bloodGlucoseDao()
    }
  }
}