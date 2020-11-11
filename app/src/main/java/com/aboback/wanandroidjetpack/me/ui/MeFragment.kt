package com.aboback.wanandroidjetpack.me.ui

import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseViewModelFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.me.viewmodel.MeViewModel

/**
 * @author jhb
 * @date 2020/10/30
 */
class MeFragment : BaseViewModelFragment<MeViewModel>(R.layout.fragment_me, MeViewModel::class.java) {

    override fun onEvent() {
        super.onEvent()
        GlobalSingle.isLoginSuccess.observe(this, Observer {
            if (it) {
                mRealVM.lgCoinUserInfo()
            } else {
                mRealVM.resetLoginState()
            }
        })
    }

}