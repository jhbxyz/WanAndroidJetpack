package com.aboback.wanandroidjetpack.view

import android.app.Application
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.base.BaseLayoutViewModel

/**
 * Created by jhb on 2020/3/26.
 */
class LoadingDialogVM(app: Application) : BaseLayoutViewModel(app) {

    val tip: ObservableField<String> = ObservableField()

    val icon: ObservableInt = ObservableInt()

    val isProgress: ObservableField<Boolean> = ObservableField()

    val progress: ObservableInt = ObservableInt(R.drawable.loading_gif)


}