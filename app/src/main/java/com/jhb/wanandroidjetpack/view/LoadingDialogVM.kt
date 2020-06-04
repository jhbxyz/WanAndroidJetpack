package com.jhb.wanandroidjetpack.view

import android.app.Application
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel

/**
 * Created by jhb on 2020/3/26.
 */
class LoadingDialogVM(app: Application) : BaseLayoutViewModel(app) {

    val tip: ObservableField<String> = ObservableField()

    val icon: ObservableInt = ObservableInt()

    val isProgress: ObservableField<Boolean> = ObservableField()

    val progress: ObservableInt = ObservableInt(R.drawable.loading_gif)


}