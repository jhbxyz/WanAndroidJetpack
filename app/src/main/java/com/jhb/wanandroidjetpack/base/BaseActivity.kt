package com.jhb.wanandroidjetpack.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jhb.wanandroidjetpack.bridge.ShareViewModel
import com.jhb.wanandroidjetpack.util.ActivityUtil

/**
 * Created by jhb on 2020-01-14.
 */
open class BaseActivity : AppCompatActivity() {

    lateinit var mShareViewModel: ShareViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtil.addActivity(this)
        mShareViewModel = getAppViewModelProvider().get(ShareViewModel::class.java)

    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityUtil.removeActivity(this)

    }

    protected fun getAppViewModelProvider(): ViewModelProvider {
        return (application as WanApp).getAppViewModelProvider(this)
    }


}