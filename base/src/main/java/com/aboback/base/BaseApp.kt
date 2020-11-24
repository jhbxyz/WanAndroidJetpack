package com.aboback.base

import android.app.Application
import android.content.Context
import com.aboback.base.util.logWithTag
import com.facebook.stetho.Stetho
import com.tencent.mmkv.MMKV

/**
 * Created by jhb on 2020-01-14.
 */
open class BaseApp : Application() {

    var mTag = javaClass.simpleName

    companion object {
        lateinit var instance: BaseApp
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        initSDKs()

    }

    private fun initSDKs() {
        Stetho.initializeWithDefaults(this)
        val rootDir = MMKV.initialize(this)
        "mmkv root: $rootDir".logWithTag("MMKV")
    }


}