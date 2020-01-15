package com.jhb.wanandroidjetpack.main

import android.os.Bundle
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseVMActivity
import com.jhb.wanandroidjetpack.databinding.ActivityMainBinding
import com.jhb.wanandroidjetpack.main.viewmodel.MainVM

class MainActivity : BaseVMActivity<MainVM, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainVM = initViewModel(MainVM::class.java)
        val binding = getLayoutId(R.layout.activity_main)
        binding.vm = mainVM
    }




}




