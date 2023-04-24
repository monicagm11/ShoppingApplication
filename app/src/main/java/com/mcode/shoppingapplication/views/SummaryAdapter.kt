package com.mcode.shoppingapplication.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mcode.shoppingapplication.databinding.ViewholderSummaryBinding
import com.mcode.shoppingapplication.models.ItemShop

class SummaryAdapter(var itemList: List<ItemShop>): RecyclerView.Adapter<SummaryViewHolder>()  {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ViewholderSummaryBinding.inflate(inflater, parent, false)
        return SummaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        holder.bindData(itemList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }
}