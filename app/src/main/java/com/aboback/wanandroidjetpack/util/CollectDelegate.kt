package com.aboback.wanandroidjetpack.util

import androidx.lifecycle.MutableLiveData
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.network.NetConstant
import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.login.ui.LoginActivity

/**
 * @author jhb
 * @date 2020/11/10
 */

fun BaseLayoutViewModel.collectDelegate(id: Int, repo: NetRepository, data: List<ItemHomeVM>) {
    launch {
        val bean = repo.collect(id)
        when (bean.errorCode) {
            NetConstant.SUCCESS -> {
                data.find { it.mId == id }?.mCollect?.set(true)
                collectSuccess()
                GlobalSingle.onCollectChange.value = true
            }
            NetConstant.UN_LOGIN -> {
                bean.errorMsg?.showToast()
                startActivity(LoginActivity::class.java)
            }
            else -> {
                bean.errorMsg?.showToast()
            }
        }
    }
}

fun BaseLayoutViewModel.unCollectDelegate(id: Int, repo: NetRepository, data: List<ItemHomeVM>, isOnMe: Boolean = false, originId: Int = -1) {
    launch {
        val bean = repo.unCollect(id, isOnMe, originId)
        when (bean.errorCode) {
            NetConstant.SUCCESS -> {
                data.find { it.mId == id }?.mCollect?.set(false)
                cancelCollect()
                GlobalSingle.onCollectChange.value = true
            }
            NetConstant.UN_LOGIN -> {
                bean.errorMsg?.showToast()
                startActivity(LoginActivity::class.java)
            }
            else -> {
                bean.errorMsg?.showToast()
            }
        }
    }
}






