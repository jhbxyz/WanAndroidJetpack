package com.jhb.wanandroidjetpack.main.viewmodel

import com.jhb.wanandroidjetpack.base.BaseViewModel
import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.net.WanObserver
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.util.subIoObsMain
import java.util.concurrent.TimeUnit

/**
 * Created by jhb on 2020-01-15.
 */
class MainVM : BaseViewModel() {


    fun lgCollectList() {
        WanService.api.lgCollectList()
            .delay(2000, TimeUnit.MILLISECONDS)
            .subIoObsMain(object : WanObserver<BaseBean>() {
                override fun onSuccess(t: BaseBean) {

                }
            })


    }

}