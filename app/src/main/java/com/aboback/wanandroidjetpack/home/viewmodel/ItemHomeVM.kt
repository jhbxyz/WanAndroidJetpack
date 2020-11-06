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

    var mTagVM1 = ObservableField<TagViewModel>()
    var mTagVM2 = ObservableField<TagViewModel>()
    var mTagVM3 = ObservableField<TagViewModel>()

    fun bindData() {
        setTitle()
        setTime()
        setAuthor()
        setCategory()
        setTags()
    }

    private fun setTags() {
        if (!bean.tags.isNullOrEmpty()) {
            val tags = bean.tags!!
            when (tags.size) {
                3 -> {
                    mTagVM1.set(TagViewModel().apply {
                        mContent.set(tags[0].name)

                    })
                    mTagVM2.set(TagViewModel().apply {
                        mContent.set(tags[1].name)

                    })
                    mTagVM3.set(TagViewModel().apply {
                        mContent.set(tags[2].name)
                    })
                }
                2 -> {
                    mTagVM2.set(TagViewModel().apply {
                        mContent.set(tags[0].name)

                    })
                    mTagVM3.set(TagViewModel().apply {
                        mContent.set(tags[1].name)

                    })
                }
                1 -> {

                    mTagVM3.set(TagViewModel().apply {
                        mContent.set(tags[0].name)
                    })
                }
            }
        }

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