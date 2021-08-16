package com.example.products.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.products.R
import com.example.products.databinding.ActivityDetailProductActivtyBinding
import com.example.products.models.ErrorProduct
import com.example.products.models.Producto
import com.example.products.models.RequestProduct

class DetailProductActivty : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductActivtyBinding
    private var productDetail: Producto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productDetail = intent.getSerializableExtra("product") as Producto?

        fillInfoProduct()

    }

    private fun getInfoProduct(id: String){
    }

    private fun fillInfoProduct(){
        with(binding){
            tvNameDetail.text = productDetail!!.name
            tvDescFull.text = productDetail!!.description
            Glide.with(this@DetailProductActivty)
                .load(productDetail!!.imageDetail)
                .into(imgProductDetail)

        }
    }
}