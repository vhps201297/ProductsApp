package com.example.products.models

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object RequestProduct {
    var retrofit: Retrofit? = null
    const val BASE_URL = "https://www.serverbpw.com/cm/2021-2/"

    fun getBaseRequest(): Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getProducts(listener: Request.ResponseListProducts){
        val callProducts: Call<List<Producto>> = getBaseRequest().create(ProductApi::class.java).getProducts()
        callProducts.enqueue(object: Callback<List<Producto>>{
            override fun onResponse(
                call: Call<List<Producto>>,
                response: Response<List<Producto>>
            ) {
                listener.isRequestSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                throw ErrorProduct(t.message)
            }
        })
    }

    fun getProductById(id:String, listener:Request.ResponseProduct){
        val callProduct: Call<Producto> = getBaseRequest().create(ProductApi::class.java).getDetailProduct(id)
        callProduct.enqueue(object: Callback<Producto>{
            override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
                listener.isRequestSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<Producto>, t: Throwable) {
                throw ErrorProduct(t.message)
            }

        })
    }

    interface Request {
        interface ResponseListProducts{
            fun isRequestSuccess(products: List<Producto>)
        }

        interface ResponseProduct{
            fun isRequestSuccess(product: Producto)
        }

    }
}