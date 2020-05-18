package com.jhb.wanandroidjetpack.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import com.jhb.wanandroidjetpack.view.LoadingDialog

/**
 * Created by jhb on 2020-01-15.
 */
open class BaseSimpleVMActivity<VM>(@LayoutRes private val layoutId: Int, private val vm: BaseLayoutViewModel) : BaseActivity() {

    var mRealVM = vm as VM
    lateinit var mBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loadingDialog = LoadingDialog(this)


        ViewModelProviders.of(this).get(BaseViewModel::class.java)


        vm.onCreate()
        vm.initShareViewModel(mShareViewModel)


        mBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, layoutId)
        mBinding.setVariable(vm.getVariableId(), vm)
        mBinding.executePendingBindings()

        vm.onModelBind()
        vm.onEvent()

        vm.mShareViewModel.loadingDialogState.observe(this, Observer {
            if (it) loadingDialog.show() else loadingDialog.dismiss()
        })
    }

    override fun onStart() {
        super.onStart()
        vm.mFinishSingle.observe(this, Observer {
            finish()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}