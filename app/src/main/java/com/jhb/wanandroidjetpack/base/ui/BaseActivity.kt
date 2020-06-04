package com.jhb.wanandroidjetpack.base.ui

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jhb.wanandroidjetpack.base.WanApp
import com.jhb.wanandroidjetpack.bridge.ShareViewModel
import com.jhb.wanandroidjetpack.util.ActivityUtil
import com.jhb.wanandroidjetpack.util.logE
import com.jhb.wanandroidjetpack.view.LoadingDialog

/**
 * Created by jhb on 2020-01-14.
 */
open class BaseActivity : AppCompatActivity() {

    var baseTag = javaClass.simpleName

    lateinit var mDialog: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtil.addActivity(this)
        mDialog = LoadingDialog(this)


    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityUtil.removeActivity(this)

    }


}