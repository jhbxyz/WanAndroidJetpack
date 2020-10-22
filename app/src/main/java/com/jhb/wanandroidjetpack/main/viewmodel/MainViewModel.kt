package com.jhb.wanandroidjetpack.main.viewmodel

import android.app.Application
import com.aboback.viewmodel.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.callback.GlobalSingle

/**
 * Created by jhb on 2020-01-15.
 */
class MainViewModel(app: Application) : BaseLayoutViewModel(app) {


    fun onHomeClick() {
        GlobalSingle.homeNavClick.value = 0
    }

    fun onCategoryClick() {
        GlobalSingle.homeNavClick.value = 1

    }

    fun onCollectClick() {
        GlobalSingle.homeNavClick.value = 2

    }


}