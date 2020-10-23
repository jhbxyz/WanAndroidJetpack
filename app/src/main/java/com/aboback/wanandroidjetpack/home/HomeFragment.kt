package com.aboback.wanandroidjetpack.home

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R

/**
 * Created by jhb on 2020-03-11.
 */
class HomeFragment : BaseVMRepositoryFragment<HomeVM>(R.layout.fragment_home) {

    override fun getViewModel(application: Application): HomeVM = HomeVM(application)


}