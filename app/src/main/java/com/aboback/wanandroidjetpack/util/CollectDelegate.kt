package com.aboback.wanandroidjetpack.util

import com.aboback.base.viewmodel.BaseViewModel
import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM

/**
 * @author jhb
 * @date 2020/11/10
 */
fun collectDelegate(vm: BaseViewModel, id: Int, repo: NetRepository, data: List<ItemHomeVM>) {

    vm.launch {

        repo.collect(id)
        data.find { it.mId == id }?.mCollect?.set(true)
        collectSuccess()
    }
}

fun BaseViewModel.unCollectDelegate(id: Int, repo: NetRepository, data: List<ItemHomeVM>) {
    launch {
        repo.unCollect(id)
        data.find { it.mId == id }?.mCollect?.set(false)
        cancelCollect()
    }
}