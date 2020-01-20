package com.jhb.wanandroidjetpack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by jhb on 2020-01-19.
 */
abstract class BaseVMFragment<V : ViewModel, L : ViewDataBinding>(@LayoutRes private val layoutId: Int) : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var mL: L
    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = DataBindingUtil.inflate<L>(inflater, layoutId, container, false)
        mL = inflate
        return inflate.root
    }

    fun getViewBinding(): L {
        return mL
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private var mVM: ViewModel? = null
    fun initViewModel(clazz: Class<V>): V {
        val vm = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(clazz)
        mVM = vm
        return vm
    }


}