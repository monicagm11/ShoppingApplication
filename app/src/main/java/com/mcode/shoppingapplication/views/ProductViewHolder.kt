package com.mcode.shoppingapplication.views

import androidx.recyclerview.widget.RecyclerView
import com.mcode.shoppingapplication.databinding.ViewholderProductItemBinding
import com.mcode.shoppingapplication.models.ItemShop

class ProductViewHolder(var binding: ViewholderProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(product: ItemShop){
        binding.item = product
        binding.executePendingBindings()
    }
}