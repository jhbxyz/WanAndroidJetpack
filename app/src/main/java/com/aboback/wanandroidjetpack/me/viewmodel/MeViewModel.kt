package com.aboback.wanandroidjetpack.me.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.rv.QuickMultiAdapter
import com.aboback.base.util.randomInt
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.network.util.MmkvUtil
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.login.ui.LoginActivity
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.response
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @author jhb
 * @date 2020/10/30
 */
class MeViewModel(app: Application) : BaseLayoutViewModel(app) {


    private var mData = arrayListOf<BaseMultiItemViewModel>()
    private val mAdapter = QuickMultiAdapter(mData).apply {
        addType(R.layout.item_rv_me_header, ItemType.ITEM_ME_HEADER)
        addType(R.layout.item_rv_me_item, ItemType.ITEM_ME_ITEM)
    }

    var rvVM = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapter)
    }


    override fun onModelBind() {
        super.onModelBind()
        mData.add(MeHeaderVM(getApplication()))

        for (i in 0..5) {
            mData.add(MeItemVM(getApplication()).apply {
                mContent.set("item = $i")
            })
        }
        mAdapter.notifyDataSetChanged()

        lgCoinUserInfo()

    }

    fun lgCoinUserInfo() {
        if (!WanApp.isLogin) {
            "请登录".showToast()
            resetLoginState()
            return
        }

        viewModelScope.launch {
            val userInfoBean = async {
                WanServer.api.lgCoinUserInfo()
            }.await()

            (mData[0] as MeHeaderVM).apply {
                loadAvatar()
                mUserName.set(userInfoBean.data?.username)
                mIdAndRank.set("id : ${userInfoBean.data?.userId}   排名 : ${userInfoBean.data?.rank}")
            }

            mAdapter.notifyItemChanged(0)

        }

    }


    fun resetLoginState() {
        if (mData.isEmpty()) return
        (mData[0] as? MeHeaderVM)?.apply {
            mPath.set(UN_LOGIN_PATH)
            mUserName.set("请登录")
            mIdAndRank.set("id : -- 排名 : --")
        }
    }

    fun loginOut() {
        launch {
            response(WanServer.api.userLogout()) {
                MmkvUtil.clearCookies()
                WanApp.isLogin = false
                GlobalSingle.isLoginSuccess.value = false
                resetLoginState()
            }
        }

    }

}