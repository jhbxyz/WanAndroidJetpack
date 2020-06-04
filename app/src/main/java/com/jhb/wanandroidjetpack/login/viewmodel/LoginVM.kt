package com.jhb.wanandroidjetpack.login.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.login.model.UserLoginBean
import com.jhb.wanandroidjetpack.main.MainActivity
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.net.WanSubscriber
import com.jhb.wanandroidjetpack.util.showToast
import com.jhb.wanandroidjetpack.viewmodel.TitleVM
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by jhb on 2020-01-14.
 */
class LoginVM(app: Application) : BaseLayoutViewModel(app) {

    var mUserName = ObservableField<String>()
    var mPassword = ObservableField<String>()

    var mTitleVM = TitleVM(
            app,
            leftDrawable = null,
            leftAction = {
                finish()
            }, title = "登录"
    )

    @SuppressLint("CheckResult")
    fun onLogin() {
        if (mUserName.get()?.isEmpty() != false) {
            "请输入账号".showToast()
            return
        }

        if (mPassword.get()?.isEmpty() != false) {
            "请输入密码".showToast()
            return
        }
        WanService.api.userLogin(mUserName.get(), mPassword.get())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : WanSubscriber<UserLoginBean>() {
                override fun onSuccess(t: UserLoginBean) {
                    startActivity(MainActivity::class.java)
                    finish()
                }
            }).addToDisposable()

    }
}