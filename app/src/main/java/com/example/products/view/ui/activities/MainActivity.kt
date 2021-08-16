package com.example.products.view.ui.activities

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.products.R
import com.example.products.databinding.ActivityMainBinding
import com.example.products.models.ErrorProduct
import com.example.products.models.Producto
import com.example.products.models.RequestProduct
import com.example.products.view.adapters.ProductAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainActivity : AppCompatActivity(), ProductAdapter.OnItemListener {

    private lateinit var binding: ActivityMainBinding
    private var productList: ArrayList<Producto> = ArrayList()
    private var adapter: ProductAdapter? = null
    private var listener: ProductAdapter.OnItemListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            adapter = ProductAdapter(productList, this@MainActivity, this@MainActivity)
            rvProducts.adapter = adapter
        }
        fillProducts()

    }

    fun fillProducts(){
        try {
            RequestProduct.getProducts(object: RequestProduct.Request.ResponseListProducts{
                override fun isRequestSuccess(products: List<Producto>) {
                    binding.progressHorizontal.visibility = View.INVISIBLE
                    productList.clear()
                    productList.addAll(products)
                    adapter!!.notifyDataSetChanged()
                }
            })
        }catch (e: ErrorProduct){
            binding.progressHorizontal.visibility = View.INVISIBLE
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
        }


    }

    override fun onCLickItemListener(id: String) {
        binding.progressHorizontal.visibility = View.VISIBLE

        try {
            RequestProduct.getProductById(id, object: RequestProduct.Request.ResponseProduct{
                override fun isRequestSuccess(product: Producto) {
                    binding.progressHorizontal.visibility = View.INVISIBLE
                    val intent = Intent(this@MainActivity, DetailProductActivty::class.java)
                    intent.putExtra("product", product)
                    startActivity(intent)
                }
            })
        }catch (e:ErrorProduct){
            binding.progressHorizontal.visibility = View.INVISIBLE
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}