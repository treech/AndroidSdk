package io.github.treech.common.config

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import io.github.treech.common.config.CommonLibConfig.Companion.instance
import io.github.treech.common.ext.util.getApplicationByReflect

class InitProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        var application = context?.applicationContext
        if (application == null) {
            application = getApplicationByReflect()
        }
        instance!!.init(application as Application)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}