package com.aboback.wanandroidjetpack.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseItemViewModel
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.rv.QuickItemViewModel
import com.aboback.wanandroidjetpack.bean.ArticleDatasBean
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel

/**
 * @author jhb
 * @date 2020/10/23
 */
class ItemHomeVM(app: Application) : BaseMultiItemViewModel(app) {
    var mTitle = ObservableField("")
    var mTime = ObservableField("")
    var mAuthor = ObservableField("")
    var mCategory = ObservableField("")

    var mTagVMList = mutableListOf<TagViewModel>()
    var mTagVM = ObservableField<TagViewModel>()

    override val itemType = ItemType.ITEM_HOME_MAIN

}