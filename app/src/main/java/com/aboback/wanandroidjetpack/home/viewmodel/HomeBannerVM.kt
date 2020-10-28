package com.aboback.wanandroidjetpack.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel

/**
 * @author jhb
 * @date 2020/10/27
 */
class HomeBannerVM(app: Application) : BaseMultiItemViewModel(app) {

    var mContent = ObservableField("Banner")

    override val itemType: Int = ItemType.ITEM_HOME_BANNER
}