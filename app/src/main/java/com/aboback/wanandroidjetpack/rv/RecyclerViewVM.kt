package com.aboback.wanandroidjetpack.rv

import android.app.Application
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by jhb on 2020-01-19.
 */
open class RecyclerViewVM(app: Application) {

    var mRefreshEnable = false
    var mIsRefreshing = ObservableField(false)

    var mAdapterObservable: ObservableField<RecyclerView.Adapter<BaseViewHolder>> = ObservableField()
    var mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(app)

    var mOnRefresh = {}

    var mOnLoadMoreListener = {}


}