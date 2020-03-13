package com.jhb.wanandroidjetpack.base

import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jhb.wanandroidjetpack.bridge.ShareViewModel
import com.jhb.wanandroidjetpack.util.ActivityUtil

/**
 * Created by jhb on 2020-01-14.
 */
open class BaseViewModel : AndroidViewModel(WanApp.instance) {

    var mFinishSingle = MutableLiveData<Boolean>()

    lateinit var mShareViewModel: ShareViewModel

    fun initShareViewModel(shareViewModel: ShareViewModel) {
        mShareViewModel = shareViewModel
    }

    open fun onCreate() {

    }

    open fun onModelBind() {

    }

    open fun onEvent() {

    }

    fun startActivity(clazz: Class<*>) {

        val intent = Intent(WanApp.instance, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        WanApp.instance.startActivity(intent)

    }


    fun finish() {
        mFinishSingle.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()

    }

}