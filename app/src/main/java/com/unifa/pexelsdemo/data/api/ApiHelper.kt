package com.unifa.pexelsdemo.data.api

import com.unifa.pexelsdemo.data.model.SearchImageResult

class ApiHelper(private val apiService: ApiService) {

    suspend fun getImagesBySearch(keywd:String, pgno:Int,page:Int):SearchImageResult = apiService.getImagesBySearch(keywd,pgno,page)
}