package com.unifa.pexelsdemo.data.repository

import com.unifa.pexelsdemo.data.api.ApiHelper
import com.unifa.pexelsdemo.data.model.SearchImageResult

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getImagesBySearch(keywd:String, pgno:Int): SearchImageResult = apiHelper.getImagesBySearch(keywd,pgno)
}