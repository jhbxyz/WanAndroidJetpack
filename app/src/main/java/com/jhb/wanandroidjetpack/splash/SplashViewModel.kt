package com.jhb.wanandroidjetpack.splash

import android.app.Application
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.base.viewmodel.BaseViewModel
import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.login.LoginActivity
import com.jhb.wanandroidjetpack.main.MainActivity
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.net.WanSubscriber
import com.jhb.wanandroidjetpack.util.SpUtil
import com.jhb.wanandroidjetpack.util.WanExecutors
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by jhb on 2020-01-14.
 */
class SplashViewModel(app: Application) : BaseLayoutViewModel(app) {

    override fun onModelBind() {
        super.onModelBind()
        goWhichPage()


    }

    fun goWhichPage() {
        if (SpUtil.isCookieExist()) {
            page(MainActivity::class.java)

        } else {
            page(LoginActivity::class.java)
        }


    }

    private fun page(clazz: Class<*>, time: Long = 500) {
        WanExecutors.MainThreadExecutor(time).execute {
            startActivity(clazz)
            finish()
        }
    }

}