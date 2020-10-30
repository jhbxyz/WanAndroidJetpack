package com.aboback.wanandroidjetpack.main.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.bridge.UnPeekLiveData
import com.aboback.wanandroidjetpack.viewmodel.FabViewModel

/**
 * Created by jhb on 2020-01-15.
 */
class MainViewModel(app: Application) : BaseLayoutViewModel(app) {


    var mFabClick = MutableLiveData(false)
    var mFabVM = FabViewModel(
            onClick = {
                mFabClick.value = true
            }
    )
    var mFabVisible = ObservableField(false)


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