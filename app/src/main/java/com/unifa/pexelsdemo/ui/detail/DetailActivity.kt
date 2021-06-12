package com.unifa.pexelsdemo.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.unifa.pexelsdemo.R
import com.unifa.pexelsdemo.data.api.ApiHelper
import com.unifa.pexelsdemo.data.api.RetrofitBuilder
import com.unifa.pexelsdemo.databinding.ActivityDetailBinding
import com.unifa.pexelsdemo.databinding.ActivityMainBinding
import com.unifa.pexelsdemo.ui.base.ViewModelFactory
import com.unifa.pexelsdemo.ui.main.MainViewModel
import com.unifa.pexelsdemo.util.Constant.IMAGEURL

class DetailActivity : AppCompatActivity() {
    lateinit var imageUrl:String
    private lateinit var viewModel: DetailViewModel
    private lateinit var mActivityDetailBinding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        imageUrl = intent.getStringExtra(IMAGEURL).toString()

        mActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        mActivityDetailBinding.vm = viewModel
        Glide.with(this).load(imageUrl).placeholder(R.drawable.ic_image_placeholder).into(mActivityDetailBinding.image)

    }
}