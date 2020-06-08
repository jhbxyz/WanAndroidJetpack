package com.jhb.wanandroidjetpack.login.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import com.google.gson.Gson
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.login.model.UserLoginBean
import com.jhb.wanandroidjetpack.main.MainActivity
import com.jhb.wanandroidjetpack.net.NetConstant
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.net.WanSubscriber
import com.jhb.wanandroidjetpack.util.SpUtil
import com.jhb.wanandroidjetpack.util.logE
import com.jhb.wanandroidjetpack.util.showToast
import com.jhb.wanandroidjetpack.viewmodel.TitleVM
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.ResponseBody

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