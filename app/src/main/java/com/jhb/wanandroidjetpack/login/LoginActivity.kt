package com.jhb.wanandroidjetpack.login

import android.os.Bundle
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.ui.BaseViewModelActivity
import com.jhb.wanandroidjetpack.databinding.ActivityLoginBinding
import com.jhb.wanandroidjetpack.login.viewmodel.LoginVM

/**
 * Created by jhb on 2020-01-14.
 */
class LoginActivity : BaseViewModelActivity<LoginVM>(R.layout.activity_login,LoginVM::class.java) {
}