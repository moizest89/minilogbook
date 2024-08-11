package com.moizest89.roche.presentation.logbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moizest89.roche.domain.model.BloodGlucoseModel
import com.moizest89.roche.presentation.R
import com.moizest89.roche.presentation.logbook.BloodGlucoseAdapter.BloodGlucoseViewHolder
import javax.inject.Inject

class BloodGlucoseAdapter @Inject constructor(): RecyclerView.Adapter<BloodGlucoseViewHolder>() {
  private val entries: MutableList<BloodGlucoseModel> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodGlucoseViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.item_blood_glucose_entry, parent, false)
    return BloodGlucoseViewHolder(view)
  }

  override fun onBindViewHolder(holder: BloodGlucoseViewHolder, position: Int) {
    val entry = entries[position]
    holder.textView.text = String.format(
      holder.getContext().getString(R.string.simple_text_gb_format),
      entry.value,
      entry.unit.prefix
    )
  }

  override fun getItemCount(): Int {
    return entries.size
  }

  fun updateEntries(items: List<BloodGlucoseModel>) {
    entries.clear()
    entries.addAll(items)
    notifyDataSetChanged()
  }

  class BloodGlucoseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.textView)

    fun getContext() = itemView.context
  }
}