package com.jhb.wanandroidjetpack.category.self.viewmodel

import android.app.Application
import com.jhb.wanandroidjetpack.BR
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.category.question.ui.DailyQuestionActivity
import com.jhb.wanandroidjetpack.category.interview_related.ui.InterviewRelatedActivity

/**
 * Created by jhb on 2020-01-19.
 */
class CategoryFraVM(app: Application) : BaseLayoutViewModel(app) {


    fun onDailyQuestion() {
        startActivity(DailyQuestionActivity::class.java)
    }

    fun interviewRelated() {
        startActivity(InterviewRelatedActivity::class.java)
    }

}