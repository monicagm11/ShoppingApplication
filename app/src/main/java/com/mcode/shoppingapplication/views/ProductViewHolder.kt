package com.mcode.shoppingapplication.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mcode.shoppingapplication.databinding.ViewholderProductItemBinding
import com.mcode.shoppingapplication.models.ItemShop
import java.text.DecimalFormat

class ProductViewHolder(var binding: ViewholderProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val format = DecimalFormat("#,###,###")

    fun bindData(product: ItemShop){
        binding.item = product
        binding.priceWithFormat = format.format(product.product.price)
        val count = product.count
        if(count == 0){
            binding.imageviewBuy.visibility = View.VISIBLE
            binding.textviewCount.visibility = View.INVISIBLE
        }else{
            binding.imageviewBuy.visibility = View.INVISIBLE
            binding.textviewCount.visibility = View.VISIBLE
            binding.textviewCount.text = count.toString()
        }
        binding.executePendingBindings()
    }
}