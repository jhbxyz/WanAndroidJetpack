package com.aboback.wanandroidjetpack.me.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.base.ui.BaseViewModelFragment
import com.aboback.base.util.logWithTag
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.view.EditDialog
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.common.EditDialogEvent
import com.aboback.wanandroidjetpack.me.viewmodel.MeViewModel
import com.aboback.wanandroidjetpack.view.EditPage
import com.blankj.utilcode.util.KeyboardUtils

/**
 * @author jhb
 * @date 2020/10/30
 */
class MeFragment : BaseVMRepositoryFragment<MeViewModel>(R.layout.fragment_me) {

    override fun getViewModel(app: Application) = MeViewModel(app)

    private val mDialog by lazy { EditDialog(mActivity) }

    private var mObserver = Observer<Boolean> {
        if (it) {
            mRealVM.coinUserInfo()
        } else {
            mRealVM.resetLoginState()
        }
    }

    private var mDialogObserver = Observer<EditDialogEvent> {
        if (it.page != EditPage.NONE) {
            mDialog.showDialog(page = it.page, collectContentPage = it.collectContentPage)
        } else {
            mDialog.dismiss()
            KeyboardUtils.hideSoftInputByToggle(mActivity)
        }
    }


    override fun onResume() {
        super.onResume()
        GlobalSingle.isLoginSuccess.observe(this, mObserver)
        GlobalSingle.showEditDialog.observe(this, mDialogObserver)
    }

    override fun onPause() {
        super.onPause()
        GlobalSingle.isLoginSuccess.removeObserver(mObserver)
        GlobalSingle.showEditDialog.removeObserver(mDialogObserver)

    }
}