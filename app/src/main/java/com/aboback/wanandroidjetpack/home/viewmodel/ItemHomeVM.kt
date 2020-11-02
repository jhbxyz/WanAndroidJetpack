package com.aboback.wanandroidjetpack.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel

/**
 * @author jhb
 * @date 2020/10/23
 */
class ItemHomeVM(app: Application, private val bean: ItemDatasBean) : BaseMultiItemViewModel(app) {
    var mTitle = ObservableField("")
    var mTime = ObservableField("")
    var mAuthor = ObservableField("")
    var mCategory = ObservableField("")

    var mTagVMList = mutableListOf<TagViewModel>()
    var mTagVM = ObservableField<TagViewModel>()

    fun bindData() {
        setTitle()
        setTime()
        setAuthor()
        setCategory()
    }

    fun setTitle() {
        mTitle.set(bean.title)
    }

    fun setTime() {
        mTime.set(bean.niceDate)
    }

    fun setAuthor() {
        if (bean.author.isNullOrEmpty()) {
            mAuthor.set("分享人: ${bean.shareUser}")
        } else {
            mAuthor.set("作者: ${bean.author}")
        }
    }

    fun setCategory() {
        bean.superChapterName?.let {
            mCategory.set("分类: $it")
        }
    }

    override val itemType = ItemType.ITEM_HOME_MAIN

}