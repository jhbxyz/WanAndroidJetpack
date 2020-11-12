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
 * Created by jhb on 2020/11/11.
 */
abstract class BaseVMRepositoryActivity<VM : BaseRepositoryViewModel<*>>(@LayoutRes private val layoutId: Int) : BaseActivity(), ViewState {

    lateinit var mRealVM: VM

    abstract fun getViewModel(app: Application): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetView()

        val vm = getViewModel(application)

        mRealVM = ViewModelProvider(this, BaseViewModelFactory(application, vm))[vm::class.java]
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(mRealVM.id(), mRealVM)
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