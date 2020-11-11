package com.aboback.wanandroidjetpack.me.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.aboback.base.util.ActivityUtil
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.network.util.MmkvUtil
import com.aboback.wanandroidjetpack.BuildConfig
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.login.ui.LoginActivity
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.longoutHint
import com.aboback.wanandroidjetpack.util.response
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel

/**
 * @author jhb
 * @date 2020/11/11
 */
class SettingViewModel(app: Application) : BaseLayoutViewModel(app) {


    var mTitleVM = TitleViewModel(
            leftAction = {
                finish()
            },
            title = "设置"
    )

    var mContent = """
        设置模块正在开发中......
        
            目前仅有退出登录的操作
                
                如果您没看到退出退出按钮
                
                    那应该是还没登录呢！！！
    """.trimIndent()

    var mVersionName = ObservableField("WanAndroid Version ${BuildConfig.VERSION_NAME}")
    var onLogoutClick = MutableLiveData<Boolean>()
    var isLogin = ObservableField(WanApp.isLogin)

    fun onLogout() {
        onLogoutClick.value = true
    }

    fun logout() {
        launch {
            response(WanServer.api.userLogout()) {
                MmkvUtil.clearCookies()
                WanApp.isLogin = false
                isLogin.set(false)
                GlobalSingle.isLoginSuccess.value = false
                longoutHint()
                finish()
            }
        }
    }
}