package com.mcode.shoppingapplication.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mcode.shoppingapplication.models.ItemShop
import com.mcode.shoppingapplication.repositories.ProductsRepository
import com.mcode.shoppingapplication.views.ProductAdapter

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context: Context = application

    var productList : MutableLiveData<List<ItemShop>> = MutableLiveData()
    var currentPosition: Int? = null
    var productsAdapter : MutableLiveData<ProductAdapter?> = MutableLiveData()
    var productsRepository: ProductsRepository = ProductsRepository()
    var productSelected: MutableLiveData<ItemShop?> = MutableLiveData()

    fun getProductList(){
        productList.value = productsRepository.getProductsList()
        val adapter = ProductAdapter(productList.value!!)
        productsAdapter.value = adapter
    }

    fun startObservable(){
        productsAdapter.value!!.itemSelected.observeForever { it ->
            it?.let {
                currentPosition = it
                productSelected.value = productList.value!![it]
                productsAdapter.value!!.itemSelected.value = null
            }
        }
    }

    fun setCount(count: Int){
        currentPosition?.let {
            productList.value!![it].count = count
            productsAdapter.value!!.notifyDataSetChanged()
        }
    }

    fun clearProductSelected(){
        productSelected.value = null
    }
    fun restartShopping(){
        productList.value!!.map { it ->
            it.count = 0
        }
        productsAdapter.value!!.notifyDataSetChanged()
    }
}