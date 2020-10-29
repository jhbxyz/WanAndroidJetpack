package com.aboback.base.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.aboback.base.R

/**
 * @author jhb
 * @date 2020/10/15
 */
class LoadingDialog(context: Context) : AppCompatDialog(context, R.style.LoadingDialogTheme) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
        setCanceledOnTouchOutside(true)

    }


}