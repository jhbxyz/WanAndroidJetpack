package com.aboback.wanandroidjetpack.me

import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseViewModelFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle

/**
 * @author jhb
 * @date 2020/10/30
 */
class MeFragment : BaseViewModelFragment<MeViewModel>(R.layout.activity_me, MeViewModel::class.java) {

    override fun onEvent() {
        super.onEvent()
        GlobalSingle.isLoginSuccess.observe(this, Observer {
            if (it) {
                mRealVM.lgCoinUserInfo()
            }
        })
    }

}