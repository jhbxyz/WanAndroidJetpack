package com.jhb.wanandroidjetpack.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseActivity
import com.jhb.wanandroidjetpack.base.BaseVMActivity
import com.jhb.wanandroidjetpack.databinding.ActivityLoginBinding
import com.jhb.wanandroidjetpack.login.viewmodel.LoginVM

/**
 * Created by jhb on 2020-01-14.
 */
class LoginActivity : BaseVMActivity<LoginVM, ActivityLoginBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginVM = initViewModel(LoginVM::class.java)
        val binding = getLayoutId(R.layout.activity_login)
        binding.vm = loginVM
        binding.lifecycleOwner = this

    }
}