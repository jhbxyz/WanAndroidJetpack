package com.aboback.base.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aboback.base.ViewState
import com.aboback.base.viewmodel.BaseLayoutViewModel

/**
 * @author jhb
 * @date 2020/10/21
 */
open class BaseViewModelActivity<VM : BaseLayoutViewModel>(@LayoutRes private val layoutId: Int, private val clazz: Class<VM>) : BaseActivity(), ViewState {

    lateinit var mRealVM: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetView()
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, layoutId)
        mRealVM = ViewModelProvider(this)[clazz]
        binding.setVariable(mRealVM.id(), mRealVM)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        onViewInit()

        mRealVM.setBound(intent.extras ?: Bundle())

        mRealVM.onModelBind()

        onEvent()
    }

    override fun beforeSetView() {

    }

    override fun onViewInit() {

    }

    override fun onEvent() {

        mRealVM.dialogState(this)
        mRealVM.finish(this)

    }


}