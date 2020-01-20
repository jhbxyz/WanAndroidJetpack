package com.jhb.wanandroidjetpack.collect.ui

import android.os.Bundle
import android.view.View
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseVMFragment
import com.jhb.wanandroidjetpack.category.viewmodel.CategoryFraVM
import com.jhb.wanandroidjetpack.collect.viewmodel.CollectFraVM
import com.jhb.wanandroidjetpack.databinding.FragmentCategoryBinding
import com.jhb.wanandroidjetpack.databinding.FragmentCollectBinding

/**
 * Created by jhb on 2020-01-19.
 */
class CollectFragment : BaseVMFragment<CollectFraVM, FragmentCollectBinding>(R.layout.fragment_collect) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val collectFraVM = initViewModel(CollectFraVM::class.java)
        val binding = getViewBinding()
        binding.vm = collectFraVM
    }

}