package com.aboback.wanandroidjetpack.category.question.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.wanandroidjetpack.base.BaseItemViewModel
import com.aboback.wanandroidjetpack.bean.ArticleDatasBean
import com.aboback.wanandroidjetpack.bean.WendaListBean

/**
 * Created by jhb on 2020-03-12.
 */
class ItemDailyQuestionVM(app: Application) : BaseItemViewModel(app) {

    var mBean = ObservableField<ArticleDatasBean>()


}