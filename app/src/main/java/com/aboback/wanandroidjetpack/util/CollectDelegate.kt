package com.aboback.wanandroidjetpack.util

import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.network.NetConstant
import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.find.viewmodel.ItemFindContentProjectTreeRightVM
import com.aboback.wanandroidjetpack.find.viewmodel.ItemFindContentProjectVM
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.login.ui.LoginActivity

/**
 * @author jhb
 * @date 2020/11/10
 */

data class CollectChangeBean(var isCollect: Boolean, var id: Int)

fun BaseLayoutViewModel.collectDelegate(id: Int, repo: NetRepository, success: (() -> Unit)? = null) {
    launch {
        val bean = repo.collect(id)
        when (bean.errorCode) {
            NetConstant.SUCCESS -> {
                collectSuccess()
                GlobalSingle.onCollectChange.value = CollectChangeBean(true, id)
                success?.invoke()
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

fun BaseLayoutViewModel.unCollectDelegate(id: Int, repo: NetRepository, isOnMe: Boolean = false, originId: Int = -1, success: (() -> Unit)? = null) {
    launch {
        val bean = repo.unCollect(id, isOnMe, originId)
        when (bean.errorCode) {
            NetConstant.SUCCESS -> {
                cancelCollect()
                GlobalSingle.onCollectChange.value = CollectChangeBean(false, if (isOnMe) originId else id)
                success?.invoke()
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

//
//fun BaseLayoutViewModel.collectProjectDelegate(id: Int, repo: NetRepository) {
//    launch {
//        val bean = repo.collect(id)
//        when (bean.errorCode) {
//            NetConstant.SUCCESS -> {
//                collectSuccess()
//                GlobalSingle.onCollectChange.value = CollectChangeBean(true, id)
//            }
//            NetConstant.UN_LOGIN -> {
//                bean.errorMsg?.showToast()
//                startActivity(LoginActivity::class.java)
//            }
//            else -> {
//                bean.errorMsg?.showToast()
//            }
//        }
//    }
//}
//
//fun BaseLayoutViewModel.unCollectProjectDelegate(id: Int, repo: NetRepository) {
//    launch {
//        val bean = repo.unCollect(id)
//        when (bean.errorCode) {
//            NetConstant.SUCCESS -> {
//                cancelCollect()
//                GlobalSingle.onCollectChange.value = CollectChangeBean(false, id)
//            }
//            NetConstant.UN_LOGIN -> {
//                bean.errorMsg?.showToast()
//                startActivity(LoginActivity::class.java)
//            }
//            else -> {
//                bean.errorMsg?.showToast()
//            }
//        }
//    }
//}







