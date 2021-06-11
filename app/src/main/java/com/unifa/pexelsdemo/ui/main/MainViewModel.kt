package com.unifa.pexelsdemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.unifa.pexelsdemo.data.http.Resource
import com.unifa.pexelsdemo.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getImagesBySearch(keywd:String, pgno:Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getImagesBySearch(keywd,pgno)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}