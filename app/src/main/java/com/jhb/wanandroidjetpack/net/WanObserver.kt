package com.jhb.wanandroidjetpack.net

import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.util.showToast
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created by jhb on 2020-01-13.
 */
abstract class WanObserver<T : BaseBean> : ResourceSubscriber<T>() {


    override fun onError(e: Throwable?) {


    }


    override fun onComplete() {
        onFinish()
    }


    override fun onNext(t: T) {

        when (t.errorCode) {
            NetConstant.SUCCESS -> {
                onSuccess(t)
            }
            else -> onFailed(t)
        }

    }

    abstract fun onSuccess(t: T)


    fun onFailed(t: T) {
        t.errorMsg.showToast()
    }


    fun onFinish() {

    }


}