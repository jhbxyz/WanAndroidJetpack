package com.aboback.wanandroidjetpack.me.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.util.getResDrawable
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.viewmodel.BannerViewModel

/**
 * @author jhb
 * @date 2020/10/27
 */
class MeItemVM(app: Application) : BaseMultiItemViewModel(app) {

    var mContent = ObservableField("")
    var mCoinCount = ObservableField("")
    var mIcon = ObservableField(R.drawable.jifen_ico.getResDrawable())
    var mShowDivider = ObservableField(true)
    var mShowMargin = ObservableField(false)

    var onClick = {}

    override val itemType: Int = ItemType.ITEM_ME_ITEM
}