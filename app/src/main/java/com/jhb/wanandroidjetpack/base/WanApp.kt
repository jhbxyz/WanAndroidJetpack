package com.jhb.wanandroidjetpack.base

import android.app.Application

/**
 * Created by jhb on 2020-01-14.
 */
class WanApp : Application() {

    companion object {
        lateinit var instance: WanApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }


}