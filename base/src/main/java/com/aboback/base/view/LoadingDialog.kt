package com.aboback.base.view

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import com.aboback.base.R
import com.aboback.base.util.getResColor

/**
 * @author jhb
 * @date 2020/10/15
 */
class LoadingDialog(context: Context) : AppCompatDialog(context, R.style.LoadingDialogTheme) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.statusBarColor  = R.color.colorAccent.getResColor()
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
        setCanceledOnTouchOutside(true)

    }


}