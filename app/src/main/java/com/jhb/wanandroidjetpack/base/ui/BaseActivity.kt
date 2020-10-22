package com.jhb.wanandroidjetpack.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhb.wanandroidjetpack.util.ActivityUtil
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