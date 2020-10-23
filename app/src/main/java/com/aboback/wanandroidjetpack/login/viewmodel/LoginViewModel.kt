package com.aboback.wanandroidjetpack.login.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.wanandroidjetpack.base.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.main.MainActivity
import com.aboback.wanandroidjetpack.net.NetConstant
import com.aboback.wanandroidjetpack.net.WanService
import com.aboback.wanandroidjetpack.util.SpUtil
import com.aboback.wanandroidjetpack.util.showToast
import com.aboback.wanandroidjetpack.viewmodel.TitleVM
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by jhb on 2020-01-14.
 */
class LoginViewModel(app: Application) : BaseLayoutViewModel(app) {

    var mUserName = ObservableField<String>()
    var mPassword = ObservableField<String>()

    var mTitleVM = TitleVM(
            app,
            leftDrawable = null,
            leftAction = {
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
        showLoadingDialog()
        WanService.api.userLogin(mUserName.get(), mPassword.get())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                it.response()?.apply {

                    val loginBean = body()
                    when (loginBean?.errorCode) {
                        NetConstant.SUCCESS -> {
                            val cookieSet = HashSet<String>()
                            headers().forEach { header ->
                                if (header.first == "Set-Cookie") {
                                    cookieSet.add(header.second)
                                }
                            }

                            SpUtil.saveCookies(cookieSet)
                            startActivity(MainActivity::class.java)
                            finish()

                        }
                        else -> {
                            showErrorDialog(loginBean?.errorMsg ?: "")
                        }
                    }
                }


            }, {
                it.message?.let { thx -> showErrorDialog(thx) }
                dismissDialogDelay()
            }, {
                dismissDialogDelay()
            }).addToDisposable()

    }
}