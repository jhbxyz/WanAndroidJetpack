package com.jhb.wanandroidjetpack.test

import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.BR
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel

/**
 * Created by jhb on 2020-01-20.
 */
class TestFraVM : BaseLayoutViewModel() {

    var mContent = ObservableField(this.javaClass.simpleName+"fagag")

    override fun getVariableId(): Int = BR.layout
}