package com.million.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.million.ui.R
import com.million.ui.databinding.ActivityFloatDragBinding
import com.million.ui.widget.FloatDragView

class FloatDragViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFloatDragBinding
    private var marginStart = 0
    private var marginTop = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFloatDragBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFullScreen()
        setDragViewLayout()
        initView()
    }

    /**设置Activity全屏*/
    private fun setFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    /**拖动结束后 固定小球当前的layout，防止刷新UI后小球回到初始位置*/
    private fun setDragViewLayout() {
        val params = binding.dragView.layoutParams as ConstraintLayout.LayoutParams
        marginStart = params.marginStart
        marginTop = params.topMargin

        binding.dragView.mLocationListener = object : FloatDragView.LocationListener {
            override fun onLocation(x: Int, y: Int) {
                params.marginStart = marginStart + x
                params.topMargin = marginTop + y
                binding.dragView.layoutParams = params
            }
        }
    }

    private fun initView() {
        binding.btnInvalidate.setOnClickListener { showTipView() }
    }

    private fun showTipView() {
        binding.tvDescribe.visibility = View.VISIBLE
        binding.tvDescribe.postDelayed({ binding.tvDescribe.visibility = View.GONE }, 1000)
    }

}