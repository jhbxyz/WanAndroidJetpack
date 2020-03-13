package com.jhb.wanandroidjetpack.question.viewmodel

import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.base.BaseItemViewModel
import com.jhb.wanandroidjetpack.bean.WendaListBean

/**
 * Created by jhb on 2020-03-12.
 */
class ItemDailyQuestionVM(bean: WendaListBean.DataBean.DatasBean) : BaseItemViewModel() {

    var mBean = ObservableField(bean)


}