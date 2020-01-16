package com.jhb.wanandroidjetpack.login.viewmodel

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.viewmodel.TitleVM
import com.jhb.wanandroidjetpack.base.BaseViewModel
import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.login.model.UserLoginBean
import com.jhb.wanandroidjetpack.main.MainActivity
import com.jhb.wanandroidjetpack.net.WanObserver
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.util.logE
import com.jhb.wanandroidjetpack.util.showToast
import com.jhb.wanandroidjetpack.util.subIoObsMain
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.Result

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
            .subIoObsMain(object : WanObserver<UserLoginBean>() {
                override fun onSuccess(t: UserLoginBean) {
                    startActivity(MainActivity::class.java)
                    finish()
                }
            })

//        WanService.api.userLoginResult(mUserName.get()!!, mPassword.get()!!)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                    {
//                        val response = it.response()
//                        val headers = response?.headers()
//
//                        val cookie = headers?.get("Set-Cookie")
//                        "cookie = $cookie".logE()
//
//                        val map = hashMapOf<String, String>()
//                        cookie?.split(";")?.forEach {
//                            it.split("=").forEach {
//                                map.put(it[0].toString(), it[1].toString())
//                            }
//                        }
//
//                        map.entries.forEach {
//                            val key = it.key
//                            val value = it.value
//                            "map  for  key = $key   value  = $value".logE()
//                        }
//
//                    },
//                    {
//
//
//                    },
//                    {
//
//                    }
//            )

    }
}