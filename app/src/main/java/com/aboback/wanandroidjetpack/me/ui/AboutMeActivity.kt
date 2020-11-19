package com.aboback.wanandroidjetpack.me.ui

import android.text.method.LinkMovementMethod
import com.aboback.base.ui.BaseViewModelActivity
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.me.viewmodel.AboutMeViewModel
import kotlinx.android.synthetic.main.activity_about_me.*

/**
 * @author jhb
 * @date 2020/11/19
 */
class AboutMeActivity : BaseViewModelActivity<AboutMeViewModel>(R.layout.activity_about_me, AboutMeViewModel::class.java) {

    override fun onViewInit() {
        super.onViewInit()
        tvContent.movementMethod = LinkMovementMethod.getInstance()

    }

}