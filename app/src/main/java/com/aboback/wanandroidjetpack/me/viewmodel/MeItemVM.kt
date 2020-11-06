package com.aboback.wanandroidjetpack.me.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.wanandroidjetpack.viewmodel.BannerViewModel

/**
 * @author jhb
 * @date 2020/10/27
 */
class MeItemVM(app: Application) : BaseMultiItemViewModel(app) {

    var mContent = ObservableField("Banner")

    var onClick = {}

    override val itemType: Int = ItemType.ITEM_ME_ITEM
}