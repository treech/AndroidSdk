package io.github.treech.common.ext.util

import android.os.Build
import android.util.SparseLongArray
import android.view.View
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class OnFastDoubleClickListener @JvmOverloads constructor(
    private val listener: View.OnClickListener,
    private val maxInterval: Long = 500
) : View.OnClickListener {

    private val mViewClickTimes: SparseLongArray = SparseLongArray()

    override fun onClick(v: View) {
        val currentTime = System.currentTimeMillis()
        val lastClickTime = mViewClickTimes[v.id]
        if (currentTime - lastClickTime > maxInterval) {
            listener.onClick(v)
        }
        mViewClickTimes.put(v.id, currentTime)
    }
}