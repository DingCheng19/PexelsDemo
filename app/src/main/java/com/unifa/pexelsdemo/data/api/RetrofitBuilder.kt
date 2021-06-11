package com.unifa.pexelsdemo.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://api.pexels.com/v1/"

    private fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor())
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}