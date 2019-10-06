package com.glao.superpod.utility

import android.content.Context
import android.content.SharedPreferences
import android.os.Environment
import android.util.Log
import java.io.File

class Session(val context: Context) {

    private var editor: SharedPreferences.Editor? = null
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setIsLogin() {
        editor = sharedPreferences.edit()
        editor?.putBoolean(IS_LOGIN, true)
        editor?.apply()
    }

    fun setIsLogout() {
        editor = sharedPreferences.edit()
        editor?.putBoolean(IS_LOGIN, true)
        editor?.apply()
    }

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }

    fun setIsLoginLogout() {
        editor = sharedPreferences.edit()
        editor?.putBoolean(IS_LOGIN, false)
        editor?.apply()
    }

    fun getIsLogin(): Boolean = sharedPreferences.getBoolean(IS_LOGIN, false)

    fun addExamTabData(tabName: String?, display: String?) {
        editor = sharedPreferences.edit()
        editor?.putString("TABNAME", tabName)
        editor?.putString("DISPLAY", display)
        editor?.apply()
    }

    fun performCleanup(): Boolean {
        return try {
            clearApplicationData()
            clearSession()
            clearStorage()
            true
        } catch (e: Exception) {
            Log.d("Cleanup", e.toString())
            false
        }
    }

    private fun clearStorage() {
        val dir = File(Environment.getExternalStorageDirectory().toString() + "WisOpt")
        if (dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                File(dir, children[i]).delete()
            }
        }
    }

    private fun clearApplicationData() {
        val cacheDirectory = context.cacheDir
        val applicationDirectory = File(cacheDirectory.parent)
        if (applicationDirectory.exists()) {
            val fileNames = applicationDirectory.list()
            for (fileName in fileNames) {
                if (fileName != "lib") {
                    deleteFile(File(applicationDirectory, fileName))
                }
            }
        }
    }

    private fun deleteFile(file: File?): Boolean {
        var deletedAll = true
        if (file != null) {
            if (file.isDirectory) {
                val children = file.list()
                for (i in children.indices) {
                    deletedAll = deleteFile(File(file, children[i])) && deletedAll
                }
            } else {
                deletedAll = file.delete()
            }
        }
        return deletedAll
    }

    fun getStringValue(key: String, default: String? = null) = sharedPreferences.getString(key, default)

    fun getBooleanValue(key: String, default: Boolean = false) = sharedPreferences.getBoolean(key, default)

    companion object {
        const val INTEGER_DEFAULT = "-1"
        const val STRING_DEFAULT = "NA"

        private const val IS_LOGIN = "IsLoggedIn"

        private const val PREF_NAME = "SuperpodPref"
    }
}