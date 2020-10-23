package com.aboback.wanandroidjetpack.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aboback.wanandroidjetpack.base.BaseLayoutViewModel

/**
 * Created by jhb on 2020/3/31.
 */
abstract class BaseMultiVMFragment<T : BaseLayoutViewModel>(@LayoutRes private val layoutId: Int) : BaseFragment(), IControllerEvent {


    lateinit var mBinding: ViewDataBinding
    lateinit var mRealVM: T
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        beforeSetView()
        mRealVM = getViewModel() as T
        mBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, container, false)
        mBinding.lifecycleOwner = this
        mBinding.setVariable(mRealVM.getVariableId(), mRealVM)
        mBinding.executePendingBindings()

        return mBinding.root
    }

    abstract fun getViewModel(): BaseLayoutViewModel


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