package com.aboback.wanandroidjetpack.login.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel

/**
 * Created by jhb on 2020-01-14.
 */
class LoginViewModel(app: Application) : BaseLayoutViewModel(app) {

    var mUserName = ObservableField<String>()
    var mPassword = ObservableField<String>()

    var mTitleVM = TitleViewModel(
            leftDrawable = null,
            leftAction = {
            },
            title = "登录"
    )

    @SuppressLint("CheckResult")
    fun onLogin() {
        if (mUserName.get().isNullOrEmpty()) {
            "请输入账号".showToast()
            return
        }

        if (mPassword.get().isNullOrEmpty()) {
            "请输入密码".showToast()
            return
        }

    }
}