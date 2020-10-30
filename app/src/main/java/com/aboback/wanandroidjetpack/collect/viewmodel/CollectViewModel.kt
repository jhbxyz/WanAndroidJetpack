package com.aboback.wanandroidjetpack.collect.viewmodel

import android.app.Application
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.collect.CollectRepository

/**
 * @author jhb
 * @date 2020/10/29
 */
class CollectViewModel(app: Application) : BaseRepositoryViewModel<CollectRepository>(app, CollectRepository()) {

    override fun onModelBind() {
        super.onModelBind()

    }

}