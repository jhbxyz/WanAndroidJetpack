package com.jhb.wanandroidjetpack.category.ui

import android.os.Bundle
import android.view.View
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseSimpleVMFragment
import com.jhb.wanandroidjetpack.category.viewmodel.CategoryFraVM

/**
 * Created by jhb on 2020-01-19.
 */
class CategoryFragment : BaseSimpleVMFragment<CategoryFraVM>(R.layout.fragment_category, CategoryFraVM()) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}