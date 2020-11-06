package com.aboback.wanandroidjetpack.login.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.util.logWithTag
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.network.util.MmkvUtil
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.network.NetConstant
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel

/**
 * Created by jhb on 2020-01-14.
 */
class LoginViewModel(app: Application) : BaseLayoutViewModel(app) {

    companion object {
        const val COLLECT_CONTENT_PAGE = "collect_content_page"
    }

    var mUserName = ObservableField<String>()
    var mPassword = ObservableField<String>()

    var mTitleVM = TitleViewModel(
            leftAction = {
                GlobalSingle.isLoginSuccess.value = false
                finish()
            },
            title = "登录"
    )

    private var mPage: CollectContentPage? = null
    override fun onModelBind() {
        super.onModelBind()
        mPage = mBundle.getSerializable(COLLECT_CONTENT_PAGE) as? CollectContentPage

        "mPage = $mPage".logWithTag("CollectContent")
    }

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
                        mPage?.let {
                            GlobalSingle.isLoginSuccessToCollect.value = it
                        }

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