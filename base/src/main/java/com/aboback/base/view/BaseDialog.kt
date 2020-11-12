package com.aboback.base.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.blankj.utilcode.util.ScreenUtils

/**
 * @author jhb
 * @date 2020/11/12
 */
open class BaseDialog<T : BaseLayoutViewModel>(private val layoutId: Int, val vm: Class<T>, private val activity: AppCompatActivity, theme: Int) : AppCompatDialog(activity, theme) {
    lateinit var mRealVM: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRealVM = ViewModelProvider(activity, ViewModelProvider.AndroidViewModelFactory(activity.application))[vm]
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), layoutId, null, false)

        setContentView(binding.root)

        binding.setVariable(mRealVM.id(), mRealVM)
        binding.executePendingBindings()

        setCancelable(false)
        setCanceledOnTouchOutside(true)

        val window = window
        val attributes = window?.attributes
        attributes?.width = ScreenUtils.getScreenWidth()
        attributes?.height = ScreenUtils.getScreenHeight() / 5 * 3
        window?.attributes = attributes

        onViewInit()

        mRealVM.onModelBind()

        onEvent()
    }

    open fun onViewInit() {

    }

    open fun onEvent() {

    }


}