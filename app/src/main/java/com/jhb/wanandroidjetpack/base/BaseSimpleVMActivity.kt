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
open class BaseSimpleVMActivity<V>(@LayoutRes private val layoutId: Int, private val vm: BaseLayoutViewModel) : BaseActivity() {

    var mRealVM = vm as V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, layoutId)
        binding.setVariable(vm.getVariableId(), vm)
        binding.executePendingBindings()
    }

    override fun onStart() {
        super.onStart()
        vm.mFinishSingle.observe(this, Observer {
            finish()
        })
        vm.onModelBind()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}