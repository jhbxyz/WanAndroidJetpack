package com.aboback.wanandroidjetpack.test

import androidx.recyclerview.widget.RecyclerView
import com.aboback.base.ui.BaseViewModelActivity
import com.aboback.wanandroidjetpack.R

/**
 * @author jhb
 * @date 2020/10/28
 */
class BannerActivity : BaseViewModelActivity<BannerTestVM>(R.layout.activity_banner, BannerTestVM::class.java) {

    override fun onViewInit() {
        super.onViewInit()
        var rv  = RecyclerView(this)
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        })
    }
}
