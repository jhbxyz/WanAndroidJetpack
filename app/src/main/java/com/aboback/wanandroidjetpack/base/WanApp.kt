package com.aboback.wanandroidjetpack.base

import com.aboback.base.BaseApp
import com.aboback.wanandroidjetpack.common.Constants
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.smtt.sdk.QbSdk


/**
 * Created by jhb on 2020-01-14.
 */
class WanApp : BaseApp() {

    companion object {
        var isLogin = false
        var userId: Int? = null
        var nikeName: String? = null

    }

    override fun onCreate() {
        super.onCreate()
        initSdks()
    }

    private fun initSdks() {
        QbSdk.initX5Environment(this, null)
        CrashReport.initCrashReport(applicationContext, Constants.BUGLY_APP_ID, false)

    }


}