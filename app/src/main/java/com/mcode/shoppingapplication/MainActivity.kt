package com.mcode.shoppingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mcode.shoppingapplication.databinding.ActivityMainBinding
import com.mcode.shoppingapplication.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding? = null
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding!!.lifecycleOwner = this
        binding!!.viewmodel = viewModel
        viewModel.getProductList()
        setEvents()
        setContentView(binding!!.root)
    }

    fun setEvents(){
        binding!!.buttonCancel.setOnClickListener {
            viewModel.getProductList()
        }
    }
}