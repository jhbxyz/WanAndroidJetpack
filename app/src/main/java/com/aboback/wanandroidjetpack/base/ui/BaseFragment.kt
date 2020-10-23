package com.aboback.wanandroidjetpack.base.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by jhb on 2020/3/20.
 */
open class BaseFragment : Fragment() {

    lateinit var mActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as BaseActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



}