package com.aboback.wanandroidjetpack.me.viewmodel

import android.app.Application
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.rv.QuickMultiAdapter
import com.aboback.base.util.getResDrawable
import com.aboback.base.util.showToast
import com.aboback.wanandroidjetpack.view.EditPage
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.network.util.MmkvUtil
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.bean.CoinUserInfoBean
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.common.EditDialogEvent
import com.aboback.wanandroidjetpack.me.MeRepository
import com.aboback.wanandroidjetpack.me.ui.AboutMeActivity
import com.aboback.wanandroidjetpack.me.ui.CoinRankActivity
import com.aboback.wanandroidjetpack.me.ui.SettingActivity
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.response

/**
 * @author jhb
 * @date 2020/10/30
 */
class MeViewModel(app: Application) : BaseRepositoryViewModel<MeRepository>(app, MeRepository()) {

    private var mCoinUserInfoBean: CoinUserInfoBean.Data? = null

    private var mData = arrayListOf<BaseMultiItemViewModel>()
    private val mAdapter = QuickMultiAdapter(mData).apply {
        addType(R.layout.item_rv_me_header, ItemType.ITEM_ME_HEADER)
        addType(R.layout.item_rv_me_item, ItemType.ITEM_ME_ITEM)
    }

    var rvVM = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapter)
    }

    private var mRankVM = MeItemVM(getApplication()).apply {
        mContent.set("我的积分")
        mIcon.set(R.drawable.jifen_ico.getResDrawable())
        onClick = {
            startActivity(CoinRankActivity::class.java, CoinRankViewModel.COIN_USER_INFO_BEAN to mCoinUserInfoBean)
        }
        mShowDivider.set(false)
        mShowMargin.set(true)
    }

    override fun onModelBind() {
        super.onModelBind()
        mData.add(MeHeaderVM(getApplication()))

        mData.add(mRankVM)

        mData.add(MeItemVM(getApplication()).apply {
            mContent.set("收藏文章")
            mIcon.set(R.drawable.sc_red_sroke_ico.getResDrawable())
            onClick = {
                if (WanApp.isLogin) {
                    GlobalSingle.showEditDialog.value = EditDialogEvent(page = EditPage.COLLECT_ARTICLE, collectContentPage = CollectContentPage.COLLECT_ARTICLE)
                } else {
                    "请先登录".showToast()
                }
            }
        })

        mData.add(MeItemVM(getApplication()).apply {
            mContent.set("收藏网站")
            mIcon.set(R.drawable.wangzhan_ico.getResDrawable())
            onClick = {
                if (WanApp.isLogin) {
                    GlobalSingle.showEditDialog.value = EditDialogEvent(page = EditPage.WEBSITE, collectContentPage = CollectContentPage.COLLECT_WEBSITE)
                } else {
                    "请先登录".showToast()
                }
            }
        })

        mData.add(MeItemVM(getApplication()).apply {
            mContent.set("分享文章")
            mIcon.set(R.drawable.wenzhang_ico.getResDrawable())
            mShowDivider.set(false)
            mShowMargin.set(true)
            onClick = {
                if (WanApp.isLogin) {
                    GlobalSingle.showEditDialog.value = EditDialogEvent(page = EditPage.SHARE_ARTICLE, collectContentPage = CollectContentPage.SHARE_ARTICLE)
                } else {
                    "请先登录".showToast()
                }
            }
        })

        mData.add(MeItemVM(getApplication()).apply {
            mContent.set("关于我")
            mIcon.set(R.drawable.xiaolianchenggong_ico.getResDrawable())
            mShowDivider.set(false)
            mShowMargin.set(true)
            onClick = {
                startActivity(AboutMeActivity::class.java)
            }
        })

        mData.add(MeItemVM(getApplication()).apply {
            mContent.set("设置")
            mIcon.set(R.drawable.shezhi_ico.getResDrawable())
            mShowDivider.set(false)
            onClick = {
                startActivity(SettingActivity::class.java)
            }

        })

        mAdapter.notifyDataSetChanged()

        requestServer()

    }

    private fun requestServer() {
        if (!WanApp.isLogin) {
            "请登录".showToast()
            resetLoginState()
            return
        }

        coinUserInfo()

    }

    fun coinUserInfo() {
        launch {
            mRepo.coinUserInfo()?.apply {
                mCoinUserInfoBean = this
                (mData[0] as? MeHeaderVM)?.apply {
                    loadAvatar()
                    mUserName.set(mNikeName)
                    mIdAndRank.set("id : $userId   排名 : $rank")
                    mAdapter.notifyItemChanged(0)
                }
                mRankVM.mCoinCount.set("当前积分: $coinCount")
            }
        }
    }


    fun resetLoginState() {
        if (mData.isEmpty()) return
        (mData[0] as? MeHeaderVM)?.apply {
            mPath.set(UN_LOGIN_PATH)
            mUserName.set("请登录")
            mIdAndRank.set("id : -- 排名 : --")
        }
        mRankVM.mCoinCount.set("当前积分: -- ")

    }

}