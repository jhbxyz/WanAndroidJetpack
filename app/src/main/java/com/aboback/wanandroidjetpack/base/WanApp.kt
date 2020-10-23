package com.aboback.wanandroidjetpack.base

import com.aboback.base.BaseApp
import com.tencent.smtt.sdk.QbSdk

/**
 * Created by jhb on 2020-01-14.
 */
class WanApp : BaseApp(){

    override fun onCreate() {
        super.onCreate()
        QbSdk.initX5Environment(this, null)

    }


}