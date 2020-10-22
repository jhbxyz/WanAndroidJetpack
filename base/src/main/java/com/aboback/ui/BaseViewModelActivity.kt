package com.aboback.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aboback.ViewState
import com.aboback.viewmodel.BaseLayoutViewModel

/**
 * @author jhb
 * @date 2020/10/21
 */
open class BaseViewModelActivity<T : BaseLayoutViewModel>(@LayoutRes private val layoutId: Int, private val clazz: Class<T>) : BaseActivity(), ViewState {

    lateinit var mRealVM: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeViewInit()
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, layoutId)
        mRealVM = ViewModelProvider(this)[clazz]
        binding.setVariable(mRealVM.id(), mRealVM)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        onViewInit()

        mRealVM.onModelBind()

        onEvent()
    }

    override fun beforeViewInit() {

    }

    override fun onViewInit() {

    }

    override fun onEvent() {

    }


}