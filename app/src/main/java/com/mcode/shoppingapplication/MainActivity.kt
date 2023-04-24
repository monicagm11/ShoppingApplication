package com.mcode.shoppingapplication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mcode.shoppingapplication.databinding.ActivityMainBinding
import com.mcode.shoppingapplication.models.ItemShop
import com.mcode.shoppingapplication.viewmodels.MainViewModel
import com.mcode.shoppingapplication.views.SummaryAdapter
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding? = null
    lateinit var viewModel: MainViewModel
    val format = DecimalFormat("#,###,###")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding!!.lifecycleOwner = this
        binding!!.viewmodel = viewModel
        viewModel.getProductList()
        viewModel.startObservable()
        setEvents()
        startObservables()
        setContentView(binding!!.root)
    }

    fun setEvents(){
        binding!!.buttonCancel.setOnClickListener {
            showSnackBar("Se ha cancelado la compra.")
            viewModel.restartShopping()
        }
        binding!!.buttonDone.setOnClickListener {
            showSummary()
        }
    }

    fun startObservables(){
        viewModel!!.productSelected.observeForever {
            it?.let {
                showAlertDialog(it)
                viewModel.clearProductSelected()
            }
        }
    }

    fun showAlertDialog(item: ItemShop){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_set_count)
        dialog.findViewById<AppCompatTextView>( R.id.textview_product_name).text = (item.product.name)
        dialog.findViewById<AppCompatTextView>( R.id.textview_product_price).text = format.format(item.product.price)
        val editTextCount: AppCompatEditText = dialog.findViewById(R.id.edittext_count)
        if(item.count>0){
            editTextCount.setText(item.count.toString())
        }
        dialog.findViewById<AppCompatButton>(R.id.button_cancel).setOnClickListener {
            viewModel.clearProductSelected()
            dialog.dismiss()
        }

        dialog.findViewById<AppCompatButton>(R.id.button_done).setOnClickListener {
            viewModel.setCount(validateCount(editTextCount.text.toString()))
            dialog.dismiss()
        }
        dialog.show()
    }

    fun validateCount(countText: String): Int{
        countText?.let {
            if(it.isNotEmpty()){
                return countText.toInt()
            }
        }
        return 0
    }

    fun showSnackBar(message: String){
        Snackbar.make(binding!!.root, message, Snackbar.LENGTH_LONG).show()
    }

    fun showSummary(){
        val originalList = viewModel.productList.value!!
        val list = originalList.filter { itemShop -> itemShop.count>0 }
        val total = originalList.sumOf { itemShop -> itemShop.count * itemShop.product.price }

        if(list.isEmpty()){
            showSnackBar("No ha elegido items para comprar.")
            return
        }

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_summary)

        val summaryAdapter: SummaryAdapter = SummaryAdapter(list)
        dialog.findViewById<RecyclerView>(R.id.recyclerview_summary).adapter = summaryAdapter
        dialog.findViewById<AppCompatButton>(R.id.button_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<AppCompatTextView>(R.id.totalValue).text = "TOTAL: ${format.format(total)}"

        dialog.findViewById<AppCompatButton>(R.id.button_done).setOnClickListener {
            viewModel.restartShopping()
            showSnackBar("Se ha confirmado su compra")
            dialog.dismiss()
        }
        dialog.show()

    }
}