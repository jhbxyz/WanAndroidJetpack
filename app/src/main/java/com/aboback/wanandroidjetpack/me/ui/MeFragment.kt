package com.aboback.wanandroidjetpack.me.ui

import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseViewModelFragment
import com.aboback.wanandroidjetpack.view.EditDialog
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.me.viewmodel.MeViewModel
import com.aboback.wanandroidjetpack.view.EditPage

/**
 * @author jhb
 * @date 2020/10/30
 */
class MeFragment : BaseViewModelFragment<MeViewModel>(R.layout.fragment_me, MeViewModel::class.java) {

    private val mDialog by lazy { EditDialog(mActivity) }

    override fun onEvent() {
        super.onEvent()
        GlobalSingle.isLoginSuccess.observe(this, Observer {
            if (it) {
                mRealVM.lgCoinUserInfo()
            } else {
                mRealVM.resetLoginState()
            }
        })

        GlobalSingle.showEditDialog.observe(this, Observer {
            if (it.page != EditPage.NONE) {
                mDialog.showDialog(page = it.page, collectContentPage = it.collectContentPage)
            } else {
                mDialog.dismiss()
            }
        })
    }

}