package com.example.products.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.products.R
import com.example.products.databinding.ItemProductoBinding
import com.example.products.models.Producto

class ProductAdapter(private val products: ArrayList<Producto>, private val ctx: Context,private val listener: OnItemListener): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent,false)
        val layoutInflater = LayoutInflater.from(ctx)
        val view = ItemProductoBinding.inflate(layoutInflater,parent, false)
        return ProductViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binData(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(binding: ItemProductoBinding, listener: OnItemListener): RecyclerView.ViewHolder(binding.root){
        val tvName = binding.tvNameProduct
        val tvProvider = binding.tvProvider
        val tvPrice = binding.tvPriceProduct
        val tvDelivery = binding.tvDelivery
        val imageViewCard = binding.imgProducto
        val context = binding.root.context
        val btnSee = binding.btnSee
        lateinit var producto: Producto
        val listenerButton = listener

        fun binData(product: Producto){
            producto = product
            tvName.text = product.name
            tvProvider.text = context.getString(R.string.str_provider, product.provider)
            tvPrice.text = context.getString(R.string.str_price,product.price)
            tvDelivery.text = context.getString(R.string.str_costo_envio,product.delivery)
            Glide.with(context)
                .load(product.urlImg)
                .into(imageViewCard)
            btnSee.setOnClickListener { listenerButton.onCLickItemListener(product.id) }
        }
    }

    interface OnItemListener {
        fun onCLickItemListener(id: String)
    }

}