package com.aboback.wanandroidjetpack.collect.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.util.truely
import com.aboback.wanandroidjetpack.base.X5WebActivity
import com.aboback.wanandroidjetpack.base.X5WebViewModel
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.common.CommonItemBean
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel

/**
 * @author jhb
 * @date 2020/10/23
 */
class ItemCollectWebsiteVM(app: Application) : BaseMultiItemViewModel(app) {

    var mTitle = ObservableField("")
    var mLink = ObservableField("")

    var mId: Int? = null

    var onEdit = {}

    var onDel = {}

    fun onItemClick() {
        startActivity(X5WebActivity::class.java,
                X5WebViewModel.FLAG_BEAN to CommonItemBean(mId, mTitle.get(), mLink.get(), false),
                X5WebViewModel.FLAG_SHOW_COLLECT_ICON to false
        )
    }

    override val itemType = ItemType.ITEM_COLLECT_WEBSITE

}