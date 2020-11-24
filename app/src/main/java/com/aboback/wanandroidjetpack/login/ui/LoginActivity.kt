package com.aboback.wanandroidjetpack.login.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryActivity
import com.aboback.base.ui.BaseViewModelActivity
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.login.viewmodel.LoginViewModel

/**
 * Created by jhb on 2020-01-14.
 */
class LoginActivity : BaseVMRepositoryActivity<LoginViewModel>(R.layout.activity_login) {

    override fun getViewModel(app: Application) = LoginViewModel(app)

    override fun onEvent() {
        super.onEvent()
        GlobalSingle.isLoginSuccess.observe(this, Observer<Boolean> {
            if (it) {
                finish()
            }
        })
    }


}