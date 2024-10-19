package com.million.ui.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

object ScreenUtil {
    /**
     * dp2px
     */
    fun dp2px(context: Context, dp: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(context: Context): Int {
        val dm = DisplayMetrics()
        (context as? Activity)?.windowManager?.defaultDisplay?.getMetrics(dm)
        return dm.heightPixels
    }

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(context: Context): Int {
        val dm = DisplayMetrics()
        (context as? Activity)?.windowManager?.defaultDisplay?.getMetrics(dm)
        return dm.widthPixels
    }
}