package com.aboback.wanandroidjetpack.category.self.viewmodel

import android.app.Application
import com.aboback.wanandroidjetpack.BR
import com.aboback.wanandroidjetpack.base.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.category.question.ui.DailyQuestionActivity
import com.aboback.wanandroidjetpack.category.interview_related.ui.InterviewRelatedActivity

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