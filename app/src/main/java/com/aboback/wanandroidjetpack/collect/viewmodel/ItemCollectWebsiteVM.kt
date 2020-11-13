package com.aboback.wanandroidjetpack.collect.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.util.truely
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
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

    override val itemType = ItemType.ITEM_COLLECT_WEBSITE

}