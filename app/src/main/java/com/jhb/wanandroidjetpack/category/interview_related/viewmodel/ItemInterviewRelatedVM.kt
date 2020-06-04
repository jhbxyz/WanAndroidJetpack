package com.jhb.wanandroidjetpack.category.interview_related.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.base.BaseItemViewModel
import com.jhb.wanandroidjetpack.bean.ArticleListBean
import com.jhb.wanandroidjetpack.bean.WendaListBean

/**
 * Created by jhb on 2020-03-12.
 */
class ItemInterviewRelatedVM(app: Application) : BaseItemViewModel(app) {

    var mBean = ObservableField<ArticleListBean.DataBean.DatasBean>()


}