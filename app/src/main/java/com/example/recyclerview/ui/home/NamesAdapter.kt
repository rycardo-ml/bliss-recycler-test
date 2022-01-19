package com.example.recyclerview.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.databinding.RowNamesBinding

class NamesAdapter(private val onClick: (String) -> Unit): RecyclerView.Adapter<NamesViewHolder>() {

    private val dataSet = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_names, parent, false)

        return NamesViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateDataSet(names: List<String>) {
        dataSet.clear()
        dataSet.addAll(names)

        notifyDataSetChanged()
    }
}

class NamesViewHolder(itemView: View, val onClick: (String) -> Unit): RecyclerView.ViewHolder(itemView) {

    private val binding = RowNamesBinding.bind(itemView)
    private var _name: String? = null

    init {
        super.itemView.setOnClickListener {
            _name?.let {
                onClick(it)
            }
        }
    }

    fun bind(name: String) {
        _name = name
        binding.tvName.text = name
    }

}


