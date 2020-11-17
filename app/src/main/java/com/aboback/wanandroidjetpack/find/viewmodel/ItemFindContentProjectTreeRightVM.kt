package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.aboback.base.rv.BaseItemViewModel
import com.aboback.base.util.getRandomColor
import com.aboback.wanandroidjetpack.base.X5WebActivity
import com.aboback.wanandroidjetpack.base.X5WebViewModel
import com.aboback.wanandroidjetpack.common.CommonItemBean

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
    var mLink: String? = null
    var mChecked = ObservableField<Boolean>(false)
    var onClickItem = {}
    var mContent = ObservableField<String>()
    var mCollect = ObservableBoolean()
    var onCollectClick = {}

    fun onItemClick() {
        startActivity(X5WebActivity::class.java,
                X5WebViewModel.FLAG_BEAN to CommonItemBean(mId, mTitle.get(), mLink, false)
        )
    }


}