package io.github.treech.util

import android.app.Application
import android.content.Context

class XConfig private constructor() {

    private lateinit var mApplication: Application

    @Volatile
    private var sDebug = false

    val application: Application
        get() {
            Preconditions.checkNotNull(mApplication, "Please call the XConfig#init() first")
            return mApplication
        }

    val context: Context
        get() {
            Preconditions.checkNotNull(mApplication, "Please call the XConfig#init() first")
            return mApplication
        }

    fun init(application: Application) {
        mApplication = application
    }


    fun setDebug(debug: Boolean) {
        sDebug = debug
    }

    companion object {

        @Volatile
        private var sInstance: XConfig? = null

        @JvmStatic
        val instance: XConfig?
            get() {
                if (sInstance == null) {
                    synchronized(XConfig::class.java) {
                        if (sInstance == null) {
                            sInstance = XConfig()
                        }
                    }
                }
                return sInstance
            }
    }
}