package com.aboback.wanandroidjetpack.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.wanandroidjetpack.base.BaseItemViewModel
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel

/**
 * @author jhb
 * @date 2020/10/23
 */
class ItemHomeVM(app: Application) : BaseItemViewModel(app) {
    var mTitle = ObservableField("")
    var mTime = ObservableField("")
    var mAuthor = ObservableField("")
    var mCategory = ObservableField("")

    var mTagList = mutableListOf<TagViewModel>()
    var mTagVM = ObservableField<TagViewModel>()

}