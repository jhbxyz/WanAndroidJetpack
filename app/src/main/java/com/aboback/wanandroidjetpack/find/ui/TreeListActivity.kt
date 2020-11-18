package com.aboback.wanandroidjetpack.find.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryActivity
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.find.viewmodel.TreeListViewModel
import com.aboback.wanandroidjetpack.util.CollectChangeBean

/**
 * @author jhb
 * @date 2020/11/17
 */
class TreeListActivity : BaseVMRepositoryActivity<TreeListViewModel>(R.layout.activity_tree_list) {

    override fun getViewModel(app: Application) = TreeListViewModel(app)

    private val mObserver = Observer<CollectChangeBean> {
        mRealVM.updateCollectState(it)
    }

    override fun onResume() {
        super.onResume()
        GlobalSingle.onCollectChange.observe(this, mObserver)
    }

    override fun onPause() {
        super.onPause()
        GlobalSingle.onCollectChange.removeObserver(mObserver)
    }

}