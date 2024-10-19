package com.million.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import com.million.ui.R

/**
 * Created by yue on 2024/10/13
 * Describe : 可随意拖拽的悬浮View
 */
class FloatDragView : FrameLayout, View.OnTouchListener {

    private var mViewWidth = 0
    private var mViewHeight = 0
    private var mDownX = 0F
    private var mDownY = 0F
    private var isMove = false

    private var mStartX = 0
    private var mStartY = 0

    var mLocationListener: LocationListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        initView()
    }

    private fun initView() {
        val lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams = lp

        val childView = getChildView()
        addView(childView)
        setOnTouchListener(this)

        post {
            // 获取一下view宽高备用
            mViewWidth = this.width
            mViewHeight = this.height
            // 获取初始绝对坐标
            initStartLocation()
        }
    }

    private fun initStartLocation() {
        val locations = getAbsLocation()
        mStartX = locations[0]
        mStartY = locations[1]
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = event.x
                mDownY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                isMove = true
                //上下偏移，传入值>0 向下偏移，传入值<0向上偏移，会触发重绘
                offsetTopAndBottom((y - mDownY).toInt())
                offsetLeftAndRight((x - mDownX).toInt())
            }

            MotionEvent.ACTION_UP -> {
                if (isMove) {
                    //do something
                }
                isMove = false
                val locations = getAbsLocation()
                mLocationListener?.onLocation(
                    locations[0] - mStartX,
                    locations[1] - mStartY
                )
            }
        }
        return true
    }

    private fun getChildView(): View {
        val imageView = ShapeableImageView(context)
        imageView.setImageResource(R.drawable.icon_float)
        imageView.post {
            val radius = imageView.width / 2f
            imageView.shapeAppearanceModel =
                ShapeAppearanceModel().toBuilder().setAllCornerSizes(radius).build()
        }
        return imageView
    }

    private fun getAbsLocation(): IntArray {
        val locations = IntArray(2)
        getLocationOnScreen(locations)
        return locations
    }

    interface LocationListener {
        /**
         * 回调当前移动距离
         * @param x:X轴移动距离
         * @param y:Y轴移动距离
         */
        fun onLocation(x: Int, y: Int)
    }
}