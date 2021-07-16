package io.github.treech.common.config

import android.app.Application
import io.github.treech.common.ext.util.Preconditions

class CommonLibConfig private constructor() {

    private lateinit var mApplication: Application

    @Volatile
    private var sDebug = false

    val application: Application
        get() {
            Preconditions.checkNotNull(mApplication, "Please call the CommonLibConfig#init() first")
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
        private var sInstance: CommonLibConfig? = null

        @JvmStatic
        val instance: CommonLibConfig?
            get() {
                if (sInstance == null) {
                    synchronized(CommonLibConfig::class.java) {
                        if (sInstance == null) {
                            sInstance = CommonLibConfig()
                        }
                    }
                }
                return sInstance
            }
    }
}