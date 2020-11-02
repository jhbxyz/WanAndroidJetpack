package com.aboback.base

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.aboback.base.util.logWithTag
import com.facebook.stetho.Stetho
import com.tencent.mmkv.MMKV

/**
 * Created by jhb on 2020-01-14.
 */
open class BaseApp : Application(), ViewModelStoreOwner {

    var mTag = javaClass.simpleName

    companion object {
        lateinit var instance: BaseApp
    }

    //TODO tip：可借助 Application 来管理一个应用级 的 SharedViewModel，
    // 实现全应用范围内的 生命周期安全 且 事件源可追溯的 视图控制器 事件通知。

    private lateinit var mAppViewModelStore: ViewModelStore
    private lateinit var mFactory: ViewModelProvider.AndroidViewModelFactory

    override fun onCreate() {
        super.onCreate()
        instance = this
        mAppViewModelStore = ViewModelStore()
        initSDKs()

    }

    private fun initSDKs() {
        Stetho.initializeWithDefaults(this)

        val rootDir = MMKV.initialize(this)
        "mmkv root: $rootDir".logWithTag("MMKV")
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    fun getAppViewModelProvider(activity: Activity?): ViewModelProvider {
        return ViewModelProvider(this, getAppFactory(activity))
    }

    fun getAppFactory(activity: Activity?): ViewModelProvider.AndroidViewModelFactory {
        val application = checkApplication(activity)
        mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        return mFactory
    }

    private fun checkApplication(activity: Activity?): Application {
        if (activity == null) {
            throw IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.")
        }
        return activity.application
    }


}