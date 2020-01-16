package com.jhb.wanandroidjetpack.util

import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.SharedElementCallback
import com.jhb.wanandroidjetpack.base.SimpleObserver
import com.jhb.wanandroidjetpack.base.WanApp
import com.jhb.wanandroidjetpack.callback.DelayCallBack
import com.jhb.wanandroidjetpack.common.CommonValue
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by jhb on 2020-01-14.
 */
fun String.logE() {
    Log.e(CommonValue.TAG, this)
}

fun String.cNamelogE(msg: String) {
    Log.e("$this   $msg", this)
}

fun String.showToast() {
    Toast.makeText(WanApp.instance, this, Toast.LENGTH_SHORT).show()
}

fun Int.getDrawable() = ActivityCompat.getDrawable(WanApp.instance, this)

fun Int.getResString() = WanApp.instance.getString(this)

fun <T> Observable<T>.subIoObsMain(observer: Observer<T>) {
    this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer)
}

fun Int.delay() {
    Observable
        .timer(this.toLong(), TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : SimpleObserver<Long>() {
            override fun onComplete() {
                super.onComplete()
            }
        })
}











