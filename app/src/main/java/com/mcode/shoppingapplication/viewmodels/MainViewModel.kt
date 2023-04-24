package com.mcode.shoppingapplication.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mcode.shoppingapplication.repositories.ProductsRepository
import com.mcode.shoppingapplication.views.ProductAdapter

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context: Context = application

    var productsAdapter : MutableLiveData<ProductAdapter?> = MutableLiveData()
    var productsRepository: ProductsRepository = ProductsRepository()

    fun getProductList(){
        val list = productsRepository.getProductsList()
        val adapter = ProductAdapter(list)
        productsAdapter.postValue(adapter)

    }
}