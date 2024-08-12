package com.moizest89.roche.domain.usecase

import com.moizest89.roche.domain.model.BloodGlucoseModel
import com.moizest89.roche.domain.model.BloodGlucoseUnit.MG_DL
import com.moizest89.roche.domain.model.BloodGlucoseUnit.MMOL_L
import com.moizest89.roche.domain.repository.BloodGlucoseRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.math.roundToInt

class GetAverageBloodGlucoseUseCaseTest {
  private val repository: BloodGlucoseRepository = mockk()
  private lateinit var getAverageBloodGlucoseUseCase: GetAverageBloodGlucoseUseCase

  @Before
  fun setUp() {
    getAverageBloodGlucoseUseCase = GetAverageBloodGlucoseUseCase(repository)
  }

  @Test
  fun `WHEN have a simple MG_DL THEN calculate the correct conversion to MMOL_L`() = runBlocking {
    val entries = listOf(
      BloodGlucoseModel(5.5, MMOL_L),
    )
    every { repository.getEntries() } returns flowOf(entries)

    var expected = 0.0
    getAverageBloodGlucoseUseCase.invoke(MG_DL).collect {
      expected = it
    }
    assertEquals(expected.roundToDecimalPlaces(), 99.10)
  }

  @Test
  fun `WHEN have a simple MMOL_L THEN calculate the correct conversion to MG_DL`() = runBlocking {
    val entries = listOf(
      BloodGlucoseModel(100.0, MG_DL),
    )
    every { repository.getEntries() } returns flowOf(entries)

    var expected = 0.0
    getAverageBloodGlucoseUseCase.invoke(MMOL_L).collect {
      expected = it
    }
    assertEquals(expected.roundToDecimalPlaces(), 5.55)
  }

  @Test
  fun `WHEN have a simple list of MG_DL THEN calculate average correctly in MG_DL`() = runBlocking {
    // Arrange
    val entries = listOf(
      BloodGlucoseModel(100.0, MG_DL),
      BloodGlucoseModel(200.0, MG_DL),
      BloodGlucoseModel(300.0, MG_DL)
    )
    every { repository.getEntries() } returns flowOf(entries)

    var expected = 0.0
    getAverageBloodGlucoseUseCase.invoke(MG_DL).collect {
      expected = it
    }
    assertEquals(expected, 200.0)
  }

  @Test
  fun `WHEN have a list of MMOL_L THEN calculate average correctly in MMOL_L`() = runBlocking {
    // Arrange
    val entries = listOf(
      BloodGlucoseModel(6.5, MMOL_L),
      BloodGlucoseModel(5.5, MMOL_L),
      BloodGlucoseModel(4.5, MMOL_L)
    )
    every { repository.getEntries() } returns flowOf(entries)

    var expected = 0.0
    getAverageBloodGlucoseUseCase.invoke(MMOL_L).collect {
      expected = it
    }
    assertEquals(expected, 5.5)
  }

  @Test
  fun `calculate average correctly with conversion to mg_dl`() = runBlocking {
    val entries = listOf(
      BloodGlucoseModel(5.0, MMOL_L),  // Should be converted to 90.091 mg/dL
      BloodGlucoseModel(126.0, MG_DL) // No conversion needed
    )
    every { repository.getEntries() } returns flowOf(entries)

    var expected = 0.0
    getAverageBloodGlucoseUseCase.invoke(MMOL_L).collect {
      expected = it
    }
    assertEquals(expected.roundToDecimalPlaces(), 6.0)
  }

  @Test
  fun `WHEN my repository is empty THEN the average should be ZERO`() = runBlocking {
    every { repository.getEntries() } returns flowOf(emptyList())
    var expected = 0.0
    getAverageBloodGlucoseUseCase.invoke(MMOL_L).collect {
      expected = it
    }
    assertEquals(expected, 0.0)
  }

  fun Double.roundToDecimalPlaces(): Double {
    return (this * 100.0).roundToInt() / 100.00
  }

}