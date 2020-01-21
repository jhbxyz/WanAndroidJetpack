package com.jhb.wanandroidjetpack.main

import android.annotation.SuppressLint
import android.os.Bundle
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseSimpleVMActivity
import com.jhb.wanandroidjetpack.category.ui.CategoryFragment
import com.jhb.wanandroidjetpack.main.viewmodel.MainVM

class MainActivity : BaseSimpleVMActivity<MainVM>(R.layout.activity_main, MainVM()) {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRealVM.lgCollectList()

        initFragment()

    }

    private fun initFragment() {

//        supportFragmentManager.beginTransaction().add(R.id.fl_container, CategoryFragment()).commitAllowingStateLoss()

    }


}




