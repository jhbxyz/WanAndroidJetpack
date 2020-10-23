package com.aboback.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aboback.base.viewmodel.BaseRepositoryViewModel

/**
 * @author jhb
 * @date 2020/10/23
 */
open class BaseViewModelFactory(app: Application, private val viewModel: BaseRepositoryViewModel<*>) : ViewModelProvider.AndroidViewModelFactory(app) {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return viewModel as T
    }

}