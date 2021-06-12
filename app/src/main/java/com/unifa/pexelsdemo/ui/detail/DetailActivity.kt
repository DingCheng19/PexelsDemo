package com.unifa.pexelsdemo.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.unifa.pexelsdemo.R
import com.unifa.pexelsdemo.databinding.ActivityDetailBinding
import com.unifa.pexelsdemo.util.Constant.IMAGEURL

class DetailActivity : AppCompatActivity() {
    lateinit var imageUrl:String
    private lateinit var viewModel: DetailViewModel
    private lateinit var mActivityDetailBinding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActionBar()
        imageUrl = intent.getStringExtra(IMAGEURL).toString()

        mActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        mActivityDetailBinding.vm = viewModel
        Glide.with(this).load(imageUrl).placeholder(R.drawable.ic_image_placeholder).into(mActivityDetailBinding.image)

    }

    private fun initActionBar() {
        val actionBar: ActionBar? = this.supportActionBar?.apply {
            title = "Details"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}