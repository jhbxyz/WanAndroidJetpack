package com.jhb.wanandroidjetpack.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel

/**
 * Created by jhb on 2020/3/20.
 */
open class BaseViewModelFragment<VM : BaseLayoutViewModel>(@LayoutRes private val layoutId: Int, private val clazz: Class<VM>) : BaseFragment(), IControllerEvent {

    lateinit var mBinding: ViewDataBinding
    lateinit var mRealVM: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        beforeSetView()
        mRealVM = ViewModelProvider(this)[clazz]
        mBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, container, false)
        mBinding.lifecycleOwner = this
        mBinding.setVariable(mRealVM.getVariableId(), mRealVM)
        mBinding.executePendingBindings()

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewInit()

        mRealVM.onModelBind()

        onEvent()
    }

    override fun beforeSetView() {

    }

    override fun onViewInit() {

    }

    override fun onEvent() {

    }

}