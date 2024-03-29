package com.aboback.wanandroidjetpack.me.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryActivity
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.me.viewmodel.SettingViewModel
import com.aboback.wanandroidjetpack.util.DialogUtil

/**
 * @author jhb
 * @date 2020/11/11
 */
class SettingActivity : BaseVMRepositoryActivity<SettingViewModel>(R.layout.activity_setting) {

    override fun getViewModel(app: Application) = SettingViewModel(app)

    override fun onEvent() {
        super.onEvent()
        mRealVM.onLogoutClick.observe(this, Observer {
            if (it) {
                mRealVM.onLogoutClick.value = false
                DialogUtil.showDialog(this, message = "您确定要退出吗?", positiveAction = { dialog ->
                    dialog.dismiss()
                    mRealVM.logout()
                })
            }
        })
    }

}