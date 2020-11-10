package com.aboback.wanandroidjetpack.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.util.truely
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel

/**
 * @author jhb
 * @date 2020/10/23
 */
class ItemHomeVM(app: Application, private val bean: ItemDatasBean? = null) : BaseMultiItemViewModel(app) {
    var mTitle = ObservableField("")
    var mTime = ObservableField("")
    var mAuthor = ObservableField("")
    var mCategory = ObservableField("")
    var mId: Int? = null
    var mCollect = ObservableBoolean()

    var mTagVM1 = ObservableField<TagViewModel>()
    var mTagVM2 = ObservableField<TagViewModel>()
    var mTagVM3 = ObservableField<TagViewModel>()
    var mTagVM4 = ObservableField<TagViewModel>()

    var onCollectClick = {}

    fun bindData() {
        setTitle()
        setTime()
        setAuthor()
        setCategory()
        setTags()
        mId = bean?.id
        mCollect.set(bean?.collect ?: false)
    }

    private fun addNewTags(bean: ItemDatasBean?) {
        val tempTags = arrayListOf<ItemDatasBean.TagBean>()
        bean?.tags?.let { tag -> tempTags.addAll(tag) }
        if (bean?.fresh.truely()) {
            tempTags.add(ItemDatasBean.TagBean("新"))
        }
        bean?.tags = tempTags
    }

    private fun setTags() {
        addNewTags(bean)

        if (!bean?.tags.isNullOrEmpty()) {
            val tags = bean?.tags!!
            when (tags.size) {
                4 -> {
                    mTagVM1.set(TagViewModel().apply {
                        mContent.set(tags[0].name)
                    })
                    mTagVM2.set(TagViewModel().apply {
                        mContent.set(tags[1].name)
                    })
                    mTagVM3.set(TagViewModel().apply {
                        mContent.set(tags[2].name)
                    })
                    mTagVM4.set(TagViewModel().apply {
                        mContent.set(tags[3].name)
                    })
                }
                3 -> {
                    mTagVM2.set(TagViewModel().apply {
                        mContent.set(tags[0].name)
                    })
                    mTagVM3.set(TagViewModel().apply {
                        mContent.set(tags[1].name)
                    })
                    mTagVM4.set(TagViewModel().apply {
                        mContent.set(tags[2].name)
                    })
                }
                2 -> {
                    mTagVM3.set(TagViewModel().apply {
                        mContent.set(tags[0].name)
                    })
                    mTagVM4.set(TagViewModel().apply {
                        mContent.set(tags[1].name)
                    })
                }
                1 -> {
                    mTagVM4.set(TagViewModel().apply {
                        mContent.set(tags[0].name)
                    })
                }
            }
        }

    }

    fun setTitle() {
        mTitle.set(bean?.title)
    }

    fun setTime() {
        mTime.set(bean?.niceDate)
    }

    fun setAuthor() {
        if (bean?.author.isNullOrEmpty()) {
            mAuthor.set("分享人: ${bean?.shareUser}")
        } else {
            mAuthor.set("作者: ${bean?.author}")
        }
    }

    fun setCategory() {
        bean?.superChapterName?.let {
            mCategory.set("分类: $it")
        }
    }

    override val itemType = ItemType.ITEM_HOME_MAIN

}