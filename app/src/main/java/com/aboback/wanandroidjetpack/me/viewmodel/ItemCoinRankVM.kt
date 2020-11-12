package com.aboback.wanandroidjetpack.me.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseItemViewModel
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.util.truely
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel

/**
 * @author jhb
 * @date 2020/10/23
 */
class ItemCoinRankVM(app: Application) : BaseItemViewModel(app) {
    var mRank = ObservableField("")
    var mName = ObservableField("")
    var mCount = ObservableField("")
}