package io.github.treech.net.utils

import android.app.Application
import android.content.Context
import androidx.annotation.RestrictTo

class ContextProvider private constructor() {

    private lateinit var mContext: Context

    companion object {

        private var INSTANCE: ContextProvider? = null

        @JvmStatic
        fun get(): ContextProvider {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ContextProvider().also {
                    INSTANCE = it
                }
            }
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    internal fun attachContext(context: Context): ContextProvider {
        mContext = context
        return this
    }

    fun getContext(): Context = mContext

    fun getApplication(): Application {
        return mContext.applicationContext as Application
    }
}