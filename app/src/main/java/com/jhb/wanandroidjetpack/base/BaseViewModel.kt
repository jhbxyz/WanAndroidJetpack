package com.jhb.wanandroidjetpack.base

import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jhb.wanandroidjetpack.util.cNamelogE
import com.jhb.wanandroidjetpack.util.logE

/**
 * Created by jhb on 2020-01-14.
 */
open class BaseViewModel : AndroidViewModel(WanApp.instance) {

    var mFinishSingle = MutableLiveData<Boolean>()


    fun startActivity(clazz: Class<*>) {

        val intent = Intent(WanApp.instance, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        WanApp.instance.startActivity(intent)

    }


    fun finish() {
        mFinishSingle.postValue(true)
    }

}