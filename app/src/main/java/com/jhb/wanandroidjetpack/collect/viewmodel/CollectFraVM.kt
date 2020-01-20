package com.jhb.wanandroidjetpack.collect.viewmodel

import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.base.BaseViewModel

/**
 * Created by jhb on 2020-01-19.
 */
class CollectFraVM : BaseViewModel() {

    var mText = ObservableField(this.javaClass.simpleName)

}