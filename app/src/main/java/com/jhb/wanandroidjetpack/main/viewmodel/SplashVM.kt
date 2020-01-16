package com.jhb.wanandroidjetpack.main.viewmodel

import com.jhb.wanandroidjetpack.base.BaseViewModel
import com.jhb.wanandroidjetpack.login.LoginActivity
import com.jhb.wanandroidjetpack.main.MainActivity
import com.jhb.wanandroidjetpack.util.SpUtil
import com.jhb.wanandroidjetpack.util.WanExecutors

/**
 * Created by jhb on 2020-01-14.
 */
class SplashVM : BaseViewModel() {

    fun goWhichPage() {
        if (SpUtil.isCookieExist()) {
            page(MainActivity::class.java)

        } else {
            page(LoginActivity::class.java)
        }


    }

    private fun page(clazz: Class<*>, time: Long = 1000) {
        WanExecutors.MainThreadExecutor(time).execute {
            startActivity(clazz)
            finish()
        }
    }

}