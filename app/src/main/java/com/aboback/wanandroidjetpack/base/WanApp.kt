package com.aboback.wanandroidjetpack.base

import android.content.Context
import com.aboback.base.BaseApp
import com.aboback.base.util.log
import com.aboback.base.util.logWithTag
import com.aboback.network.util.MmkvUtil
import com.aboback.wanandroidjetpack.db.WanDatabase
import com.aboback.wanandroidjetpack.util.WanExecutors
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

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        initUserInfo()
    }

    override fun onCreate() {
        super.onCreate()
        initSdks()
    }

    private fun initUserInfo() {
        WanExecutors.mDiskIO.execute {
            val userInfo = WanDatabase.getInstance().userLoginDao.getUserInfo()
            "initUserInfo   userInfo = $userInfo".logWithTag("22222")
            isLogin = userInfo?.mIsLogin ?: false
            userId = userInfo?.id
            nikeName = userInfo?.nickname
        }
    }

    private fun initSdks() {
        QbSdk.initX5Environment(this, null)


    }


}