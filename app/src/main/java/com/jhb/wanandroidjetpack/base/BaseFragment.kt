package com.jhb.wanandroidjetpack.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jhb.wanandroidjetpack.bridge.ShareViewModel

/**
 * Created by jhb on 2020-01-19.
 */
open class BaseFragment : Fragment() {

    var mActivity: Activity? = null

    lateinit var mShareViewModel: ShareViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity

        mShareViewModel = getAppViewModelProvider().get(ShareViewModel::class.java)

    }


    protected open fun getAppViewModelProvider(): ViewModelProvider {
        return (mActivity?.getApplicationContext() as WanApp).getAppViewModelProvider(mActivity)
    }

}