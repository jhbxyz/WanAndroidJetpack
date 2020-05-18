package com.jhb.wanandroidjetpack.view

import android.app.Dialog
import android.os.Bundle
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseActivity

/**
 * Created by jhb on 2020-03-13.
 */
class LoadingDialog(activity: BaseActivity) : Dialog(activity, R.style.LoadingDialogTheme) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)

    }


}

