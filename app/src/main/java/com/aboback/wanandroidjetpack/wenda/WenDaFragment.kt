package com.aboback.wanandroidjetpack.wenda

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R

/**
 * @author jhb
 * @date 2020/10/27
 */
class WenDaFragment : BaseVMRepositoryFragment<WenDaVM>(R.layout.fragment_wenda) {
    override fun getViewModel(application: Application) = WenDaVM(application)
}