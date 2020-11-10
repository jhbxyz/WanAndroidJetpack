package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.aboback.base.rv.BaseItemViewModel
import com.aboback.base.util.getRandomColor

/**
 * @author jhb
 * @date 2020/11/5
 */
class ItemFindContentProjectTreeRightVM(app: Application) : BaseItemViewModel(app) {
    var mPath = ObservableField<String>()
    var mTitle = ObservableField<String>()
    var mTime = ObservableField<String>()
    var mDesc = ObservableField<String>()
    var mAuthor = ObservableField<String>()
    var mId: Int? = 0
    var mChecked = ObservableField<Boolean>(false)
    var onClickItem = {}
    var mContent = ObservableField<String>()
    var mCollect = ObservableBoolean()
    var onCollectClick = {}
}