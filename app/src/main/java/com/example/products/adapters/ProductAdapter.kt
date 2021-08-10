package com.example.products.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.products.R
import com.example.products.models.Producto

class ProductAdapter(private val products: ArrayList<Producto>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.tvName.text = products[position].name
        holder.tvDesc.text = products[position].description
        holder.tvPrice.text = products[position].precio
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvName: TextView
        var tvDesc: TextView
        var tvPrice: TextView
        init {
            tvName = view.findViewById(R.id.tv_name_product)
            tvDesc = view.findViewById(R.id.tv_desc_product)
            tvPrice = view.findViewById(R.id.tv_price_product)
        }
    }

}