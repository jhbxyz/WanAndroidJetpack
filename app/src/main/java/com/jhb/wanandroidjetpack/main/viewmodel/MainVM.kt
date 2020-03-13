package com.jhb.wanandroidjetpack.main.viewmodel

import androidx.databinding.library.baseAdapters.BR
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.base.BaseViewModel
import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.net.WanObserver
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.util.logE
import com.jhb.wanandroidjetpack.util.subIoObsMain
import java.util.concurrent.TimeUnit

/**
 * Created by jhb on 2020-01-15.
 */
class MainVM : BaseLayoutViewModel() {


    fun lgCollectList() {
        WanService.api.lgCollectList()
            .delay(2000, TimeUnit.MILLISECONDS)
            .subIoObsMain(object : WanObserver<BaseBean>() {
                override fun onSuccess(t: BaseBean) {

                }
            })


    }

    fun onHomeClick() {
        mShareViewModel.homeNavClick.value = 0
    }

    fun onCategoryClick() {
        mShareViewModel.homeNavClick.value = 1

    }

    fun onCollectClick() {
        mShareViewModel.homeNavClick.value = 2

    }



}