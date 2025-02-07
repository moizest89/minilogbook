package com.moizest89.roche.presentation.common

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

fun Spinner.onItemClicked(onItemSelected: (position: Int) -> Unit) {
  this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
      onItemSelected(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
  }
}

fun Fragment.showMessage(@StringRes message: Int) =
  Toast.makeText(requireActivity(), getText(message), Toast.LENGTH_SHORT).show()

fun String.toValidDouble(): Double {
  return if (this.isNotEmpty()) this.toDouble() else 0.0
}

fun Double.roundToDecimalPlaces(): Double {
  return (this * 100.0).roundToInt() / 100.00
}

fun Long.formatDate(): String {
  val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
  return dateFormat.format(Date(this))
}