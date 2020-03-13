package com.jhb.wanandroidjetpack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.OnRebindCallback
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.jhb.wanandroidjetpack.BR
import com.jhb.wanandroidjetpack.util.logE

/**
 * Created by jhb on 2020-01-19.
 */
abstract class BaseSimpleVMFragment<VM>(@LayoutRes private val layoutId: Int, private val vm: BaseLayoutViewModel) : BaseFragment() {


    var mRealVM = vm as VM

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vm.initShareViewModel(mShareViewModel)

        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, container, false)
        binding.setVariable(vm.getVariableId(), vm)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        vm.onModelBind()
        vm.onEvent()
    }

    open fun initView() {

    }


}