package com.unifa.pexelsdemo.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unifa.pexelsdemo.R
import com.unifa.pexelsdemo.data.api.ApiHelper
import com.unifa.pexelsdemo.data.api.RetrofitBuilder
import com.unifa.pexelsdemo.data.http.Status
import com.unifa.pexelsdemo.data.model.PlPhoto
import com.unifa.pexelsdemo.databinding.ActivityMainBinding
import com.unifa.pexelsdemo.ui.base.ViewModelFactory
import com.unifa.pexelsdemo.ui.detail.DetailActivity
import com.unifa.pexelsdemo.util.Constant.IMAGEURL
import com.unifa.pexelsdemo.util.EndLessOnScrollListener


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mActivityMainBinding: ActivityMainBinding
    private var START_PAGE: Int = 1
    private var data: ArrayList<PlPhoto> = arrayListOf()
    private lateinit var mKeyWord: String
    private lateinit var mEndLessOnScrollListener: EndLessOnScrollListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        // 设置dataBinding、viewModel
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
        //mActivityMainBinding.viewModel = viewModel
        mActivityMainBinding.vm = viewModel
        //setupViewModel()
        setupEvents()
        //setupObservers()
        setupRecyclerView()
    }

    fun setupEvents() {
        mActivityMainBinding.button.setOnClickListener(View.OnClickListener {
            mKeyWord = mActivityMainBinding.etKeyWord.text.toString()
            if (mKeyWord.isEmpty()) {
                Toast.makeText(this, "キーワードを空栏にしてはいけません", Toast.LENGTH_SHORT).show()
            } else {
                data.clear()
                mEndLessOnScrollListener.init()
                //mActivityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
                hideKeyboard()
                getImagesBySearch(mKeyWord, START_PAGE)
            }
        })
    }

    private fun Activity.hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(findViewById<View>(android.R.id.content).windowToken, 0);
    }

//    fun View.hideKeyboard() {
//        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(windowToken, 0)
//    }
//    private fun setupViewModel() {
//        viewModel = ViewModelProviders.of(
//            this,
//            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
//        ).get(MainViewModel::class.java)
//    }

//    private fun setupObservers() {
//
//
//        viewModel.isLoading
//    }

    fun getImagesBySearch(keyWord: String, page: Int) {
        viewModel.getImagesBySearch(keyWord, page).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        mActivityMainBinding.progressBar.visibility = View.INVISIBLE
                        it.data?.let { it1 -> data.addAll(it1.photos) }
                        mActivityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        mActivityMainBinding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        mActivityMainBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        mActivityMainBinding.recyclerView.layoutManager = layoutManager
        mActivityMainBinding.recyclerView.adapter =
            PhotosRecyclerViewAdapter(this, data, object : IImageClick {
                override fun onImageClickCallBack(url: String) {
                    val intent = Intent(applicationContext, DetailActivity::class.java).apply {
                        putExtra(IMAGEURL, url)
                    }
                    startActivity(intent)
                }
            })

        mEndLessOnScrollListener = object :
            EndLessOnScrollListener(layoutManager) {
            override fun onLoadMore(currentPage: Int) {
                getImagesBySearch(mKeyWord, currentPage)
            }
        }
        mEndLessOnScrollListener.init()

        mActivityMainBinding.recyclerView.addOnScrollListener(mEndLessOnScrollListener)
    }
}