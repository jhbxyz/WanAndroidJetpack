package com.jhb.wanandroidjetpack.splash

import com.aboback.ui.BaseViewModelActivity
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.main.MainActivity
import com.jhb.wanandroidjetpack.util.delay

/**
 * Created by jhb on 2020-01-14.
 */
class SplashActivity : BaseViewModelActivity<SplashViewModel>(R.layout.activity_splash, SplashViewModel::class.java) {

    override fun onViewInit() {
        super.onViewInit()

        100.delay {
            startActivity(MainActivity::class.java)
            finish()
        }
    }
}