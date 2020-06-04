package com.jhb.wanandroidjetpack.category.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.jhb.wanandroidjetpack.BR
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseItemViewModel
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.question.ui.DailyQuestionActivity
import com.jhb.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.jhb.wanandroidjetpack.rv.RecyclerViewVM

/**
 * Created by jhb on 2020-01-19.
 */
class CategoryFraVM(app: Application) : BaseLayoutViewModel(app) {


    fun onDailyQuestion() {
        startActivity(DailyQuestionActivity::class.java)
    }


    override fun getVariableId(): Int = BR.layout


}