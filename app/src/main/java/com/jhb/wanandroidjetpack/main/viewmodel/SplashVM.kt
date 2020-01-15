package com.jhb.wanandroidjetpack.main.viewmodel

import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.base.BaseViewModel
import com.jhb.wanandroidjetpack.login.LoginActivity
import com.jhb.wanandroidjetpack.main.MainActivity
import com.jhb.wanandroidjetpack.util.WanExecutors

/**
 * Created by jhb on 2020-01-14.
 */
class SplashVM : BaseViewModel() {

    var mIsLogin = ObservableField<Boolean>(false)

    fun getLoginState() {
        mIsLogin.set(false)
    }

    fun goWhichPage() {
        if (mIsLogin.get() == true) {
            page(MainActivity::class.java)

        } else {
            page(LoginActivity::class.java)
        }


    }

    private fun page(clazz: Class<*>, time: Long = 2000) {
        WanExecutors.MainThreadExecutor(time).execute {
            startActivity(clazz)
        }
    }

}