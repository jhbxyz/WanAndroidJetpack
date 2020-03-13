package com.jhb.wanandroidjetpack.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jhb.wanandroidjetpack.util.logE

/**
 * Created by jhb on 2020-01-15.
 */
open class BaseVMActivity<V : BaseViewModel, L : ViewDataBinding> : BaseActivity() {

    private var mVM: BaseViewModel? = null
    fun initViewModel(clazz: Class<V>): V {
        val vm = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(clazz)
        mVM = vm
        return vm
    }

    fun getLayoutId(@LayoutRes layoutId: Int) = DataBindingUtil.setContentView<L>(this, layoutId)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVM?.onCreate()

        mVM?.onEvent()

    }

    override fun onStart() {
        super.onStart()

        mVM?.mFinishSingle?.observe(this, Observer {
            finish()
        })
        mVM?.onModelBind()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}