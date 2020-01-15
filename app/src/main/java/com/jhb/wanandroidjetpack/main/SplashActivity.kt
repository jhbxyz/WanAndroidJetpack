package com.jhb.wanandroidjetpack.main

import android.os.Bundle
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseVMActivity
import com.jhb.wanandroidjetpack.databinding.ActivitySplashBinding
import com.jhb.wanandroidjetpack.main.viewmodel.SplashVM

/**
 * Created by jhb on 2020-01-14.
 */
class SplashActivity : BaseVMActivity<SplashVM, ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashVM = initViewModel(SplashVM::class.java)
        val binding = getLayoutId(R.layout.activity_splash)
        binding.vm = splashVM

        splashVM.goWhichPage()
    }

}