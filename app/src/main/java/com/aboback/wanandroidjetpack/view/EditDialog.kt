package com.aboback.wanandroidjetpack.view

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aboback.base.util.delay
import com.aboback.base.util.logWithTag
import com.aboback.base.view.BaseDialog
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.common.EditDialogEventBean
import com.blankj.utilcode.util.KeyboardUtils
import kotlinx.android.synthetic.main.dialog_edit.*

/**
 * @author jhb
 * @date 2020/11/12
 */
enum class EditPage {
    COLLECT_ARTICLE, WEBSITE, SHARE_ARTICLE, NONE
}

class EditDialog(private val activity: AppCompatActivity) : BaseDialog<EditDialogViewModel>(R.layout.dialog_edit, EditDialogViewModel::class.java, activity, R.style.EditDialogTheme) {

    fun showDialog(page: EditPage, bean: EditDialogEventBean? = null, collectContentPage: CollectContentPage) {
        show()
        mRealVM.handlePageData(page, bean, collectContentPage)
        KeyboardUtils.showSoftInput(et0)
    }


}