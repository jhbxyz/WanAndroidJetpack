package com.aboback.wanandroidjetpack.util

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.aboback.base.util.isNull

/**
 * @author jhb
 * @date 2020/11/11
 */
object DialogUtil {

    fun showDialog(activity: Activity, title: String = "提示", message: String, confirmText: String = "确定", cancelText: String = "取消",
                   positiveAction: (DialogInterface) -> Unit, negativeAction: ((DialogInterface) -> Unit)? = null) {
        AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(confirmText) { dialog, which ->
                positiveAction.invoke(dialog)
            }
            .setNegativeButton(cancelText) { dialog, which ->
                if (negativeAction.isNull()) {
                    dialog.dismiss()
                } else {
                    negativeAction?.invoke(dialog)
                }
            }
            .show()
    }


}