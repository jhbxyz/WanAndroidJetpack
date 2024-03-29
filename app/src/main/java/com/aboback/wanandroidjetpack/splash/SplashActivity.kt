package com.aboback.wanandroidjetpack.splash

import com.aboback.base.ui.BaseViewModelActivity
import com.aboback.base.util.delay
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.main.ui.MainActivity

/**
 * Created by jhb on 2020-01-14.
 */
class SplashActivity : BaseViewModelActivity<SplashViewModel>(R.layout.activity_splash, SplashViewModel::class.java) {

    override fun onViewInit() {
        super.onViewInit()

        500.delay {
            startActivity(MainActivity::class.java)
            finish()
        }
    }
}