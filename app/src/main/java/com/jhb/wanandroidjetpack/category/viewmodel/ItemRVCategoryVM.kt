package com.jhb.wanandroidjetpack.category.viewmodel

import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.BR
import com.jhb.wanandroidjetpack.base.BaseItemViewModel

/**
 * Created by jhb on 2020-01-20.
 */
class ItemRVCategoryVM(var content: String) : BaseItemViewModel() {

//    var mContent = ObservableField<String>()


    override fun getVariableId(): Int = BR.item
}