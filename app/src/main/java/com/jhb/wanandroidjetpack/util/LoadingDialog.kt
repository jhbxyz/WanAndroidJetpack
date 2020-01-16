package com.jhb.wanandroidjetpack.util

import android.app.ProgressDialog
import android.widget.ProgressBar

/**
 * Created by jhb on 2020-01-16.
 */
object LoadingDialog {

    val progressBar = ProgressBar(ActivityUtil.getStackTopAct())

    val pd = ProgressDialog(ActivityUtil.getStackTopAct())

    fun show() {
        pd.setMessage("正在加载......")
//
//        WanExecutors.mMainThread.execute {
//            pd.show()
//        }
    }

    fun dismiss() {
        pd.dismiss()
    }

}