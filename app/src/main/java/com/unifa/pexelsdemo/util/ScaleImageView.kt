package com.unifa.pexelsdemo.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup.LayoutParams

class ScaleImageView : androidx.appcompat.widget.AppCompatImageView {
    private var lastX = 0f
    private var lastY // 上一次记录的点
            = 0f
    private var lastDistance //上一次两点间的距离
            = 0f

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?) : super(context!!) {}

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                lastY = event.y
            }
            MotionEvent.ACTION_MOVE -> if (event.pointerCount == 2) { //两点触摸
                val disX = Math.abs(event.getX(0) - event.getX(1)) //第一个点的偏移量
                val disY = Math.abs(event.getY(0) - event.getY(1)) //第二个点的偏移量
                val dis =
                    Math.sqrt((disX * disX + disY * disY).toDouble()).toFloat() //记录两点间的距离
                if (lastDistance == 0f) {
                    lastDistance = dis //记录第一次
                } else {
                    val scale = dis / lastDistance
                    lastDistance = dis //替换上一次
                    scaleImage(scale)
                }
            } else if (event.pointerCount == 1) { //单点触摸
                val currentX = event.x
                val currentY = event.y
                val disX = currentX - lastX
                val disY = currentY - lastY
                scrollBy((-disX).toInt(), (-disY).toInt()) //进行拖动视图
                lastX = currentX //替换上一次位置
                lastY = currentY
            }
            MotionEvent.ACTION_UP -> {
                lastX = 0f //恢复初始化状态
                lastY = 0f
                lastDistance = 0f
            }
            else -> {
            }
        }
        super.onTouchEvent(event)
        return true
    }

    /**
     * 进行缩放
     * @param scale
     */
    private fun scaleImage(scale: Float) {
        val width = width
        val newWidth = (width * scale).toInt()
        val height = height
        val newHeight = (height * scale).toInt()
        val params: LayoutParams = layoutParams
        params.height = newHeight
        params.width = newWidth
        layoutParams = params
    }
}
