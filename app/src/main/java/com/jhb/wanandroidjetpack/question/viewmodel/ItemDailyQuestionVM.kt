package com.jhb.wanandroidjetpack.question.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.base.BaseItemViewModel
import com.jhb.wanandroidjetpack.bean.WendaListBean

/**
 * Created by jhb on 2020-03-12.
 */
class ItemDailyQuestionVM(app: Application) : BaseItemViewModel(app) {

    var mBean = ObservableField<WendaListBean.DataBean.DatasBean>()


}