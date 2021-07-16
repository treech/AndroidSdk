package io.github.treech.common.ext.util

import android.view.View
import io.github.treech.common.ext.view.clickNoRepeat

/**
 * 设置点击事件
 * @param views 需要设置点击事件的view
 * @param onClick 点击触发的方法
 */
fun setOnclick(vararg views: View?, onClick: (View) -> Unit) {
    views.forEach {
        it?.setOnClickListener { view ->
            onClick.invoke(view)
        }
    }
}

/**
 * 设置防止重复点击事件
 * @param views 需要设置点击事件的view集合
 * @param interval 时间间隔 默认0.5秒
 * @param onClick 点击触发的方法
 */
fun setOnclickNoRepeat(vararg views: View?, interval: Long = 500, onClick: (View) -> Unit) {
    views.forEach {
        it?.clickNoRepeat(interval = interval) { view ->
            onClick.invoke(view)
        }
    }
}