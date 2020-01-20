package com.jhb.wanandroidjetpack.category.ui

import android.os.Bundle
import android.view.View
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseVMFragment
import com.jhb.wanandroidjetpack.category.viewmodel.CategoryFraVM
import com.jhb.wanandroidjetpack.databinding.FragmentCategoryBinding

/**
 * Created by jhb on 2020-01-19.
 */
class CategoryFragment : BaseVMFragment<CategoryFraVM, FragmentCategoryBinding>(R.layout.fragment_category) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryFraVM = initViewModel(CategoryFraVM::class.java)
        val binding = getViewBinding()
        binding.vm = categoryFraVM
    }

}