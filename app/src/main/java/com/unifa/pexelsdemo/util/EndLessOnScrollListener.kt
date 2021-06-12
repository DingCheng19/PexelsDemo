package com.unifa.pexelsdemo.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class EndLessOnScrollListener(  //声明一个LinearLayoutManager
    private val mLinearLayoutManager: LinearLayoutManager
) :
    RecyclerView.OnScrollListener() {
    //当前页，从0开始
    private var currentPage = 1
    //已经加载出来的Item的数量
    private var totalItemCount = 0
    //主要用来存储上一个totalItemCount
    private var previousTotal = 0
    //在屏幕上可见的item数量
    private var visibleItemCount = 0
    //在屏幕可见的Item中的第一个
    private var firstVisibleItem = 0
    //是否正在上拉数据
    private var loading = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        if (loading) {
            //Log.d("wnwn","firstVisibleItem: " +firstVisibleItem);
            //Log.d("wnwn","totalPageCount:" +totalItemCount);
            //Log.d("wnwn", "visibleItemCount:" + visibleItemCount)；
            if (totalItemCount > previousTotal) {
                //说明数据已经加载结束
                loading = false
                previousTotal = totalItemCount
            }
        }
        //这里需要好好理解
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem) {
            currentPage++
            onLoadMore(currentPage)
            loading = true
        }
    }

    fun init(){
        currentPage = 1
        previousTotal = 0
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     */
    abstract fun onLoadMore(currentPage: Int)
}