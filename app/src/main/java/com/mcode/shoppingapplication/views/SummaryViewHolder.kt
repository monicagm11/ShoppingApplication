package com.mcode.shoppingapplication.views

import androidx.recyclerview.widget.RecyclerView
import com.mcode.shoppingapplication.databinding.ViewholderSummaryBinding
import com.mcode.shoppingapplication.models.ItemShop
import java.text.DecimalFormat

class SummaryViewHolder(var binding: ViewholderSummaryBinding): RecyclerView.ViewHolder(binding.root) {
    val format = DecimalFormat("#,###,###")

    fun bindData(product: ItemShop){
        val priceWithFormat = format.format(product.product.price)
        val count = product.count
        val totalCost = count * product.product.price
        binding.countItem.text = count.toString()
        binding.nameItem.text = "${product.product.name} \n${priceWithFormat}"
        binding.costItem.text = format.format(totalCost)
        binding.executePendingBindings()
    }
}