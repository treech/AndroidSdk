package io.treech.sdk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.treech.sdk.R

class PopwindowActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PopwindowActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popwindow)
    }
}