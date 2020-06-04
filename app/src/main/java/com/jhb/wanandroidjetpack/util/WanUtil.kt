package com.jhb.wanandroidjetpack.util

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jhb.wanandroidjetpack.base.WanApp
import com.jhb.wanandroidjetpack.common.CommonValue
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jhb on 2020-01-14.
 */
fun String.logE() {
    Log.e(CommonValue.TAG, this)
}

fun String.logEWhitT(tagMsg: String) {
    Log.e(tagMsg, this)
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

fun Int.delay(runnable: Runnable) {
    Handler(Looper.getMainLooper()).postDelayed(runnable, this.toLong())
}

fun Int.getResDimen() = WanApp.instance.resources.getDimension(this)

fun Int.getResDrawable() = ContextCompat.getDrawable(WanApp.instance, this)

fun Int.getResColor() = ContextCompat.getColor(WanApp.instance, this)

fun Int.delay(action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ action.invoke() }, this.toLong())
}










