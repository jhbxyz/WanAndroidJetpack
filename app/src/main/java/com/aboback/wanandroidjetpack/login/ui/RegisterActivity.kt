package com.aboback.wanandroidjetpack.login.ui

import com.aboback.base.ui.BaseViewModelActivity
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.login.viewmodel.LoginViewModel
import com.aboback.wanandroidjetpack.login.viewmodel.RegisterViewModel

/**
 * Created by jhb on 2020-11-18.
 */
class RegisterActivity : BaseViewModelActivity<RegisterViewModel>(R.layout.activity_register, RegisterViewModel::class.java)