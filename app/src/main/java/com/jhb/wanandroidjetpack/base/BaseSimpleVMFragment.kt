package com.jhb.wanandroidjetpack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.jhb.wanandroidjetpack.BR

/**
 * Created by jhb on 2020-01-19.
 */
abstract class BaseSimpleVMFragment<T>(@LayoutRes private val layoutId: Int, private val vm: BaseLayoutViewModel) : BaseFragment() {


    var mRealVM = vm as T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, container, false)
        binding?.setVariable(vm.getVariableId(), vm)
        binding?.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.onModelBind()
    }


}