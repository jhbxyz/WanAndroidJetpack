package com.jhb.wanandroidjetpack.util

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.jhb.wanandroidjetpack.base.WanApp

/**
 * Created by jhb on 2019-08-09.
 */
class SpCommon(spName: String = "") {

    private var mSpName = spName
    private val SP_USER_INFO = "sp_user_info" //default sp Name
    private var mSp: SharedPreferences

    init {
        mSp = if (TextUtils.isEmpty(mSpName)) {
            WanApp.instance.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE)
        } else {
            WanApp.instance.getSharedPreferences(mSpName, Context.MODE_PRIVATE)
        }
    }

    private val mEditor: SharedPreferences.Editor by lazy { mSp.edit() }

    fun put(key: String, value: String) {
        mEditor.putString(key, value).apply()
    }

    fun put(key: String, value: Int) {
        mEditor.putInt(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        mEditor.putBoolean(key, value).apply()
    }

    fun put(key: String, value: Long) {
        mEditor.putLong(key, value).apply()
    }

    fun put(key: String, value: Float) {
        mEditor.putFloat(key, value).apply()
    }

    fun put(key: String, value: Set<String>) {
        mEditor.putStringSet(key, value).apply()
    }

    @JvmOverloads
    fun getString(key: String, defValue: String = ""): String {
        return mSp.getString(key, defValue) ?: ""
    }

    @JvmOverloads
    fun getInt(key: String, defValue: Int = 0): Int {
        return mSp.getInt(key, defValue)
    }

    @JvmOverloads
    fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        return mSp.getBoolean(key, defValue)
    }

    @JvmOverloads
    fun getLong(key: String, defValue: Long = 0L): Long {
        return mSp.getLong(key, defValue)
    }

    @JvmOverloads
    fun getFloat(key: String, defValue: Float = 0f): Float {
        return mSp.getFloat(key, defValue)
    }

    @JvmOverloads
    fun getStringSet(key: String, defValue: Set<String>? = null): MutableSet<String>? {
        return mSp.getStringSet(key, defValue)
    }

    fun clear() {
        mEditor.clear().apply()
    }

}