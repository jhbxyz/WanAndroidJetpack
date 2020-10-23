package com.aboback.wanandroidjetpack.category.interview_related.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.wanandroidjetpack.base.BaseItemViewModel
import com.aboback.wanandroidjetpack.bean.ArticleListBean

/**
 * Created by jhb on 2020-03-12.
 */
class ItemInterviewRelatedVM(app: Application) : BaseItemViewModel(app) {

    var mBean = ObservableField<ArticleListBean.DataBean.DatasBean>()


}