package com.aboback.wanandroidjetpack.main.viewmodel

import android.app.Application
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.bridge.UnPeekLiveData

/**
 * Created by jhb on 2020-01-15.
 */
class MainViewModel(app: Application) : BaseLayoutViewModel(app) {

    val mHomeNavClick = UnPeekLiveData<Int>()

    fun onHomeClick() {
        mHomeNavClick.value = 0
    }

    fun onCategoryClick() {
        mHomeNavClick.value = 1

    }

    fun onCollectClick() {
        mHomeNavClick.value = 2

    }


    fun onFindClick() {
        mHomeNavClick.value = 3

    }

    fun onMeClick() {
        mHomeNavClick.value = 4
    }


}