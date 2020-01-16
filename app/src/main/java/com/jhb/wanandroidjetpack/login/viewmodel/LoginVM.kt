package com.jhb.wanandroidjetpack.login.viewmodel

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.viewmodel.TitleVM
import com.jhb.wanandroidjetpack.base.BaseViewModel
import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.main.MainActivity
import com.jhb.wanandroidjetpack.net.WanObserver
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.util.logE
import com.jhb.wanandroidjetpack.util.showToast
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by jhb on 2020-01-14.
 */
class LoginVM : BaseViewModel() {

    var mUserName = ObservableField<String>()
    var mPassword = ObservableField<String>()

    var mTitleVM = TitleVM(
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
        WanService.api.userLogin(mUserName.get()!!, mPassword.get()!!)
            .toFlowable(BackpressureStrategy.DROP)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : WanObserver<BaseBean>() {
                override fun onSuccess(t: BaseBean) {
                    startActivity(MainActivity::class.java)

                    finish()
                }
            })

    }
}