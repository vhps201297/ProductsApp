package com.example.products.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductApi {

    @GET("products.php")
    fun getProducts(): Call<List<Producto>>

    @GET("product_detail.php?id=")
    fun getDetailProduct(@Query("id") id:String): Call<Producto>
}