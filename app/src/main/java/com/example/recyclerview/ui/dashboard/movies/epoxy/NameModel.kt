package com.example.recyclerview.ui.dashboard

import android.view.View
import androidx.annotation.CallSuper
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.recyclerview.R
import com.example.recyclerview.databinding.RowNamesBinding

@EpoxyModelClass
abstract class NameModel: EpoxyModelWithHolder<NameHolder>() {

    @EpoxyAttribute
    lateinit var nameText: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onClickListener: (String) -> Unit

    override fun bind(holder: NameHolder) {
        super.bind(holder)

        val binding = RowNamesBinding.bind(holder.itemView)

        binding.root.setOnClickListener { onClickListener(nameText) }
        binding.tvName.text = nameText
    }

    override fun getDefaultLayout(): Int {
        return R.layout.row_names
    }
}


class NameHolder: EpoxyHolder() {

    private var view: View? = null

    val itemView: View
        get() = view!!

    @CallSuper
    override fun bindView(itemView: View) {
        this.view = itemView
    }
}