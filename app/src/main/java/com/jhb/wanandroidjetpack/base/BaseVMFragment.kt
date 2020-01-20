package com.jhb.wanandroidjetpack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * Created by jhb on 2020-01-19.
 */
abstract class BaseVMFragment<V : BaseViewModel, L : ViewDataBinding>(@LayoutRes private val layoutId: Int) : BaseFragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (mVM == null) throw IllegalAccessException("you must init ViewModel in onViewCreated lifecycle")
        mVM?.onModelBind()
    }

    private var mVM: BaseViewModel? = null
    fun initViewModel(clazz: Class<V>): V {
        val vm = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(clazz)
        mVM = vm
        return vm
    }


    fun init(){


    }


}