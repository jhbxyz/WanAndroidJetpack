package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.rv.BaseItemViewModel
import com.aboback.base.util.getRandomColor
import com.aboback.wanandroidjetpack.base.X5WebActivity
import com.aboback.wanandroidjetpack.base.X5WebViewModel
import com.aboback.wanandroidjetpack.common.CommonItemBean
import com.aboback.wanandroidjetpack.find.ui.TreeListActivity

/**
 * @author jhb
 * @date 2020/11/5
 */
class ItemFindContentTreeAndNaviRightVM(app: Application, private val isNavi: Boolean = false) : BaseItemViewModel(app) {
    var mBgColor = ObservableField(getRandomColor())
    var mContent = ObservableField<String>()
    var mCid: Int? = null
    var mLink: String? = null

    fun onItemClick() {
        if (isNavi) {
            startActivity(X5WebActivity::class.java,
                    X5WebViewModel.FLAG_BEAN to CommonItemBean(null, mContent.get(), mLink, false),
                    X5WebViewModel.FLAG_SHOW_COLLECT_ICON to false
            )
        } else {
            startActivity(TreeListActivity::class.java,
                    TreeListViewModel.TITLE to mContent.get(),
                    TreeListViewModel.CID to mCid
            )
        }

    }
}