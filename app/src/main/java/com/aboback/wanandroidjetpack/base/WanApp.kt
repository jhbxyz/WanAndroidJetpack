package com.aboback.wanandroidjetpack.base

import com.aboback.base.BaseApp
import com.aboback.base.util.log
import com.aboback.base.util.logWithTag
import com.aboback.network.util.MmkvUtil
import com.tencent.smtt.sdk.QbSdk


/**
 * Created by jhb on 2020-01-14.
 */
class WanApp : BaseApp() {

    companion object {
        var isLogin = false

    }

    override fun onCreate() {
        super.onCreate()
        isLogin = MmkvUtil.isLogin()
        "isLogin = $isLogin".logWithTag(mTag)
        initSdks()
    }

    private fun initSdks() {
        QbSdk.initX5Environment(this, null)


    }


}