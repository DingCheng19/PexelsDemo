package com.unifa.pexelsdemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.unifa.pexelsdemo.data.http.Resource
import com.unifa.pexelsdemo.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

//    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
//    var keyWord:String = ""
    //var curPage:Int = 1
    private val PER_OF_PAGE:Int = 10

    fun getImagesBySearch(keywd:String,page:Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getImagesBySearch(keywd,PER_OF_PAGE,page)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

//    fun onRefresh(){
//
//    }

//    fun onSearch(){
//       var name = keyWord
//        var a = 6
//    }
}