package com.jhb.wanandroidjetpack.util

import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.jhb.wanandroidjetpack.base.WanApp
import com.jhb.wanandroidjetpack.common.CommonValue

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