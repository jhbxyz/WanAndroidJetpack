package com.aboback.base.util

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aboback.base.BaseApp
import com.aboback.base.BuildConfig
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author jhb
 * @date 2020/10/23
 */
enum class LogEnum {
    VERBOSE, DEBUG, INFO, WARN, ERROR
}

fun String.log(logEnum: LogEnum = LogEnum.ERROR) {
    if (BuildConfig.DEBUG) {
        when (logEnum) {
            LogEnum.VERBOSE -> Log.v("jhb", this)
            LogEnum.DEBUG -> Log.d("jhb", this)
            LogEnum.INFO -> Log.i("jhb", this)
            LogEnum.WARN -> Log.w("jhb", this)
            LogEnum.ERROR -> Log.e("jhb", this)
        }
    }
}

fun String.logWithTag(tag: String, logEnum: LogEnum = LogEnum.ERROR) {
    if (BuildConfig.DEBUG) {
        when (logEnum) {
            LogEnum.VERBOSE -> Log.v(tag, this)
            LogEnum.DEBUG -> Log.d(tag, this)
            LogEnum.INFO -> Log.i(tag, this)
            LogEnum.WARN -> Log.w(tag, this)
            LogEnum.ERROR -> Log.e(tag, this)
        }
    }
}


fun String.showToast() {
    Toast.makeText(BaseApp.instance, this, Toast.LENGTH_SHORT).show()
}

fun Int.getDrawable() = ActivityCompat.getDrawable(BaseApp.instance, this)

fun Int.getResString() = BaseApp.instance.getString(this)

fun <T> Observable<T>.subIoObsMain(observer: Observer<T>) {
    this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer)
}

fun Int.delay(runnable: Runnable) {
    Handler(Looper.getMainLooper()).postDelayed(runnable, this.toLong())
}

fun Int.getResDimen() = BaseApp.instance.resources.getDimension(this)

fun Int.getResDrawable() = ContextCompat.getDrawable(BaseApp.instance, this)

fun Int.getResColor() = ContextCompat.getColor(BaseApp.instance, this)

fun Int.delay(action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ action.invoke() }, this.toLong())
}

fun Any?.isNull() = this == null

fun Any?.isNotNull() = !isNull()

fun Boolean?.truely() = this != null && this

fun Boolean?.falsely() = !truely()



