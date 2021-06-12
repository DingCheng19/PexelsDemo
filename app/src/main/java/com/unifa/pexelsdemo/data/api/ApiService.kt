package com.unifa.pexelsdemo.data.api

import com.unifa.pexelsdemo.data.model.SearchImageResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getImagesBySearch(@Query("query") keywd:String, @Query("per_page") pageno:Int, @Query("page") page:Int): SearchImageResult

}