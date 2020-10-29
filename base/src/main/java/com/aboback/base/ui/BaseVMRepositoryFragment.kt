package com.aboback.base.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.aboback.base.BaseRepository
import com.aboback.base.BaseViewModelFactory
import com.aboback.base.ViewState
import com.aboback.base.viewmodel.BaseRepositoryViewModel

/**
 * Created by jhb on 2020/3/20.
 */
abstract class BaseVMRepositoryFragment<VM : BaseRepositoryViewModel<*>>(@LayoutRes private val layoutId: Int) : BaseFragment(), ViewState {

    lateinit var mRealVM: VM

    abstract fun getViewModel(app: Application): VM


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        beforeSetView()

        val vm = getViewModel(mActivity.application)

        mRealVM = ViewModelProvider(this, BaseViewModelFactory(mActivity.application, vm))[vm::class.java]
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(mRealVM.id(), mRealVM)
        binding.executePendingBindings()

        return binding.root
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

        mRealVM.dialogState(mActivity)

    }

}