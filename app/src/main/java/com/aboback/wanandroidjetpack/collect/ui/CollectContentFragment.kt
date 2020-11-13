package com.aboback.wanandroidjetpack.collect.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.base.util.isNotNull
import com.aboback.base.util.logWithTag
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.collect.viewmodel.CollectContentVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.CollectChangeBean
import com.aboback.wanandroidjetpack.util.DialogUtil
import com.aboback.wanandroidjetpack.util.RvScrollDelegate
import com.aboback.wanandroidjetpack.view.EditDialog
import com.aboback.wanandroidjetpack.view.EditPage
import java.io.Serializable

/**
 * @author jhb
 * @date 2020/10/27
 */
enum class CollectContentPage : Serializable {
    COLLECT_ARTICLE, INTERVIEW_RELATE, SHARE_ARTICLE, COLLECT_WEBSITE, SHARE_PROJECT, NONE
}

class CollectContentFragment(private val mContentPage: CollectContentPage) : BaseVMRepositoryFragment<CollectContentVM>(R.layout.fragment_collect_content), RvScrollToTop, SelectPage {

    private val mDialog by lazy { EditDialog(mActivity) }

    private var mFragmentInit = false
    private var isTabLayoutClick = false

    override fun getViewModel(app: Application) = CollectContentVM(mContentPage, app)

    override fun onViewInit() {
        super.onViewInit()
        mFragmentInit = true
        bindScrollListener()
    }

    override fun onEvent() {
        super.onEvent()
        GlobalSingle.isLoginSuccessToCollect.observe(this, Observer {
            if (it == mContentPage) {
                mRealVM.requestServer()
                GlobalSingle.isLoginSuccessToCollect.value = CollectContentPage.NONE
            }
        })
        if (isTabLayoutClick) {
            onSelectPage()
        }

        mRealVM.mDelWebsite.observe(this, Observer { id ->
            if (id.isNotNull()) {
                DialogUtil.showDialog(mActivity, message = "您确定要删除吗?", positiveAction = {
                    mRealVM.delCollectWebsite(id!!)
                    mRealVM.mDelWebsite.value = null
                    it.dismiss()
                })
            }
        })

        mRealVM.mDeleteShareArticle.observe(this, Observer {
            if (it) {
                mRealVM.delMyArticle()
                mRealVM.mDeleteShareArticle.value = false
            }
        })

        GlobalSingle.showEditDialog.observe(this, Observer {
            if (mContentPage == it.collectContentPage) {

                when (it.collectContentPage) {
                    CollectContentPage.COLLECT_WEBSITE -> {
                        if (it.page != EditPage.NONE) {
                            mDialog.showDialog(it.page, it.bean, it.collectContentPage)
                        } else {
                            mDialog.dismiss()
                            if (it.bean.isNotNull()) {
                                mRealVM.updateWebsiteChangeItem(it.bean!!)
                            }
                        }
                    }


                }
            }

        })

        GlobalSingle.onAddCollectArticle.observe(this, Observer {
            if (it == mContentPage) {
                mRealVM.requestServer(false)
                GlobalSingle.onAddCollectArticle.value = CollectContentPage.NONE
            }
        })

    }

    private val mObserver = Observer<CollectChangeBean> {
        when (mContentPage) {
            CollectContentPage.INTERVIEW_RELATE -> mRealVM.updateCollectState(it)
            CollectContentPage.COLLECT_ARTICLE -> mRealVM.requestServer(false)
            else -> {
                //Nothing
            }
        }
    }


    private val mAddWebsiteObserver = Observer<Boolean> {
        if (it) {
            if (mContentPage == CollectContentPage.COLLECT_WEBSITE) {
                mRealVM.collectWebsite(true)
                GlobalSingle.onAddCollectWebsite.value = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        GlobalSingle.onCollectChange.observe(this, mObserver)
        GlobalSingle.onAddCollectWebsite.observe(this, mAddWebsiteObserver)
    }

    override fun onPause() {
        super.onPause()
        GlobalSingle.onCollectChange.removeObserver(mObserver)
        GlobalSingle.onAddCollectWebsite.removeObserver(mAddWebsiteObserver)
    }

    override fun bindScrollListener() {
        RvScrollDelegate.bindScrollListener(mActivity as MainActivity, mRealVM.rvVM)
    }

    override fun scrollToTop() {
        RvScrollDelegate.scrollToTop(mRealVM.rvVM)
    }

    override fun onSelectPage() {
        if (!mFragmentInit) {
            isTabLayoutClick = true
        } else {
            if (!mRealVM.isRequestSuccess) {
                mRealVM.requestServer()
            }
        }

    }

}