package com.mcode.shoppingapplication.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mcode.shoppingapplication.databinding.ViewholderProductItemBinding
import com.mcode.shoppingapplication.models.ItemShop

class ProductAdapter(var itemList: List<ItemShop>): RecyclerView.Adapter<ProductViewHolder>() {
    lateinit var context: Context
    var itemSelected = MutableLiveData<Int?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ViewholderProductItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindData(itemList[holder.adapterPosition])
        holder.itemView.setOnClickListener {
            itemSelected.value = holder.adapterPosition
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

}