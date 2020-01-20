package com.jhb.wanandroidjetpack.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseVMActivity
import com.jhb.wanandroidjetpack.base.TestType
import com.jhb.wanandroidjetpack.category.ui.CategoryFragment
import com.jhb.wanandroidjetpack.databinding.ActivityMainBinding
import com.jhb.wanandroidjetpack.main.viewmodel.MainVM

class MainActivity : BaseVMActivity<MainVM, ActivityMainBinding>() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainVM = initViewModel(MainVM::class.java)
        val binding = getLayoutId(R.layout.activity_main)
        binding.vm = mainVM


        mainVM.lgCollectList()

        initFragment()

    }

    private fun initFragment() {

        supportFragmentManager.beginTransaction().add(R.id.fl_container, CategoryFragment()).commitAllowingStateLoss()

    }


}




