package com.moizest89.roche.presentation.logbook

import android.R.layout
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moizest89.roche.domain.model.BloodGlucoseModel
import com.moizest89.roche.domain.model.BloodGlucoseUnit
import com.moizest89.roche.presentation.R
import com.moizest89.roche.presentation.common.onItemClicked
import com.moizest89.roche.presentation.common.showMessage
import com.moizest89.roche.presentation.databinding.FragmentLogbookBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LogbookFragment : Fragment() {

  private var _binding: FragmentLogbookBinding? = null
  private val binding get() = _binding!!

  private lateinit var unitSpinner: Spinner
  private lateinit var bloodGlucoseInput: EditText
  private lateinit var saveButton: Button
  private lateinit var averageLabel: TextView

  private val viewModel: LogbookViewModel by viewModels()
  private lateinit var recyclerView: RecyclerView

  @Inject
  lateinit var adapter: BloodGlucoseAdapter

  private val selectedUnit get() = unitSpinner.selectedItem as BloodGlucoseUnit

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentLogbookBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    bindViews()

    viewLifecycleOwner.lifecycleScope.launch {
      // repeatOnLifecycle ensures that this block is only running when the Lifecycle is at least STARTED
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.bloodGlucoseEntries.collect { entries ->
          updateRecyclerView(entries)
        }
        viewModel.averageBloodGlucose.collect { average ->
          setAverageLabel(average)
        }
      }
    }

    unitSpinner.onItemClicked {
      viewModel.updateAverage(selectedUnit)
    }
    saveButton.setOnClickListener {
      saveBloodGlucoseEntry()
    }
  }

  private fun bindViews() {
    unitSpinner = binding.unitSpinner
    bloodGlucoseInput = binding.bloodGlucoseInput
    saveButton = binding.saveButton
    averageLabel = binding.averageLabel
    recyclerView = binding.recyclerView
    recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    recyclerView.adapter = adapter
    ArrayAdapter(
      requireActivity(),
      layout.simple_spinner_item,
      BloodGlucoseUnit.entries.toTypedArray()
    ).also { adapter ->
      adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item)
      unitSpinner.adapter = adapter
    }
  }

  private fun setAverageLabel(averageValue: Double) {
    averageLabel.text = String.format(
      getString(R.string.simple_text_average_format),
      averageValue.toString(),
      selectedUnit.prefix
    )
  }

  private fun saveBloodGlucoseEntry() {
    val inputTextValue = bloodGlucoseInput.text.toString().toDouble()
    if (inputTextValue > 0.0) {
      viewModel.addBloodGlucoseEntry(inputTextValue, selectedUnit)
      viewModel.updateAverage(selectedUnit)
      bloodGlucoseInput.text.clear()
    } else {
      showMessage(R.string.simple_text_invalid_value)
    }
  }

  private fun updateRecyclerView(entries: List<BloodGlucoseModel>) {
    adapter.updateEntries(entries)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}