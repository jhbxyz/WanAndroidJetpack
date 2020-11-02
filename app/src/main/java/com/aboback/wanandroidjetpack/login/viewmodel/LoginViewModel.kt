package com.aboback.wanandroidjetpack.login.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.aboback.base.util.log
import com.aboback.base.util.logWithTag
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.network.util.MmkvUtil
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.network.NetConstant
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel
import kotlinx.coroutines.launch

/**
 * Created by jhb on 2020-01-14.
 */
class LoginViewModel(app: Application) : BaseLayoutViewModel(app) {

    var mUserName = ObservableField<String>()
    var mPassword = ObservableField<String>()

    var mTitleVM = TitleViewModel(
            leftAction = {
                GlobalSingle.isLoginSuccess.value = false
                finish()
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

        launch {
            WanServer.api.userLogin(mUserName.get(), mPassword.get())?.apply {
                val body = body()
                when (body?.errorCode) {
                    NetConstant.SUCCESS -> {
                        val cookieSet = HashSet<String>()
                        headers().forEach { header ->
                            if (header.first == "Set-Cookie") {
                                cookieSet.add(header.second)
                            }
                        }
                        MmkvUtil.saveCookie(cookieSet)
                        WanApp.isLogin = true
                        GlobalSingle.isLoginSuccess.value = true
                        finish()
                    }
                    else -> {
                        "${body?.errorMsg}".showToast()
                    }
                }

            }
        }

    }
}