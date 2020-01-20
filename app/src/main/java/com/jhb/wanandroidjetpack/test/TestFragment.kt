package com.jhb.wanandroidjetpack.test

import android.os.Bundle
import android.view.View
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseSimpleVMFragment

/**
 * Created by jhb on 2020-01-20.
 */
class TestFragment : BaseSimpleVMFragment<TestFraVM>(R.layout.fragment_test, TestFraVM()) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}