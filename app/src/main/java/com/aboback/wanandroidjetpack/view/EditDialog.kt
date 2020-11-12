package com.aboback.wanandroidjetpack.view

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aboback.base.util.logWithTag
import com.aboback.base.view.BaseDialog
import com.aboback.wanandroidjetpack.R

/**
 * @author jhb
 * @date 2020/11/12
 */
enum class EditPage {
    COLLECT_ARTICLE, WEBSITE, SHARE_ARTICLE
}

class EditDialog(private val activity: AppCompatActivity, private val page: EditPage) : BaseDialog<EditDialogViewModel>(R.layout.dialog_edit, EditDialogViewModel::class.java, activity, R.style.EditDialogTheme) {


    override fun show() {
        super.show()
        mRealVM.handlePageData(page)
    }

    override fun dismiss() {
        super.dismiss()
        "dismiss   EditDialog".logWithTag("22222222")
    }

    override fun onEvent() {
        super.onEvent()
        mRealVM.mDismisDialog.observeForever {
            if (it) {
                "dismiss   observeForever ".logWithTag("22222222")

                dismiss()
                mRealVM.mDismisDialog.value = false
            }
        }
    }


}