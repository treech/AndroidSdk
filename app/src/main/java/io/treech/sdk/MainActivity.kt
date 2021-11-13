package io.treech.sdk

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.treech.sdk.R
import io.github.treech.util.ToastUtils

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showToast1(view: View) {
        ToastUtils.showShort("弹出来提示了")
    }

    fun showToast2(view: View) {
        val toastLayout = ToastUtils.layoutId2View(R.layout.layout_ads_toast)
        ToastUtils.make()
            .setGravity(Gravity.CENTER, 0, 0)
            .setDurationIsLong(true)
            .show(toastLayout)
    }

    fun showToast3(view: View) {
        val toastLayout = ToastUtils.layoutId2View(R.layout.layout_ads_toast)
        val toast = Toast.makeText(this, "test center Toast", Toast.LENGTH_SHORT)
//        val toast=Toast(this)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.view = toastLayout
        toast.show()
    }
}