package com.moizest89.roche.presentation.logbook

import android.R.layout
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.moizest89.roche.domain.model.BloodGlucoseModel
import com.moizest89.roche.domain.model.BloodGlucoseUnit
import com.moizest89.roche.presentation.R
import com.moizest89.roche.presentation.common.onItemClicked
import com.moizest89.roche.presentation.common.roundToDecimalPlaces
import com.moizest89.roche.presentation.common.showMessage
import com.moizest89.roche.presentation.common.toValidDouble
import com.moizest89.roche.presentation.databinding.FragmentLogbookBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LogbookFragment : Fragment() {

  private var _binding: FragmentLogbookBinding? = null
  private val binding get() = _binding!!
  private val viewModel: LogbookViewModel by viewModels()

  @Inject
  lateinit var adapter: BloodGlucoseAdapter
  private val selectedUnit get() = binding.unitSpinner.selectedItem as BloodGlucoseUnit

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

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
        launch { collectBloodGlucoseEntries() }
        launch { collectAverageBloodGlucose() }
      }
    }
    requireActivity().addMenuProvider(object : MenuProvider {
      override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
      }

      override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
          R.id.action_clear_data -> {
            deleteAllEntries()
            true
          }

          R.id.action_about_me -> {
            aboutMeMessage()
            true
          }

          else -> false
        }
      }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
  }

  private suspend fun collectBloodGlucoseEntries() {
    viewModel.bloodGlucoseEntries.collect { entries ->
      updateRecyclerView(entries)
    }
  }

  private suspend fun collectAverageBloodGlucose() {
    viewModel.averageBloodGlucose.collect { average ->
      setAverageLabel(average)
    }
  }

  private fun deleteAllEntries() {
    AlertDialog.Builder(requireActivity())
      .setTitle(R.string.alert_clear_data_title)
      .setMessage(R.string.alert_clear_data_message)
      .setCancelable(false)
      .setPositiveButton(android.R.string.ok) { dialog, id ->
        viewLifecycleOwner.lifecycleScope.launch {
          viewModel.deleteAllEntries()
        }
      }
      .setNegativeButton(android.R.string.cancel) { dialog, id ->
        dialog.dismiss()
      }.create().show()
  }

  private fun aboutMeMessage() {
    val result = HtmlCompat.fromHtml(getString(R.string.alert_message_about_me), HtmlCompat.FROM_HTML_MODE_LEGACY)
    AlertDialog.Builder(requireActivity())
      .setTitle(R.string.alert_title_about_me)
      .setMessage(result)
      .setCancelable(false)
      .setPositiveButton(android.R.string.ok) { dialog, _ ->
        dialog.dismiss()
      }.create().show()
  }

  private fun bindViews() {
    with(binding) {
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
      binding.unitSpinner.onItemClicked {
        viewModel.updateAverage(selectedUnit)
      }
      binding.saveButton.setOnClickListener {
        saveBloodGlucoseEntry()
      }
    }
  }

  private fun setAverageLabel(averageValue: Double) {
    binding.averageLabel.text = String.format(
      getString(R.string.simple_text_average_format),
      averageValue.roundToDecimalPlaces().toString(),
      selectedUnit.prefix
    )
  }

  private fun saveBloodGlucoseEntry() {
    with(binding) {
      val inputTextValue = bloodGlucoseInput.text.toString().toValidDouble()
      if (inputTextValue > 0.0) {
        viewModel.addBloodGlucoseEntry(inputTextValue, selectedUnit)
        viewModel.updateAverage(selectedUnit)
        bloodGlucoseInput.text.clear()
      } else {
        showMessage(R.string.simple_text_invalid_value)
      }
    }
  }

  private fun updateRecyclerView(entries: List<BloodGlucoseModel>) {
    binding.textViewNoData.visibility = if (entries.isEmpty()) View.VISIBLE else View.GONE
    binding.frameLayoutHeaderTable.visibility = if (entries.isEmpty()) View.GONE else View.VISIBLE
    adapter.updateEntries(entries)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}