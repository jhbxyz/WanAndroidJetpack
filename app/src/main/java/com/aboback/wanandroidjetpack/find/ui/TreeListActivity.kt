package com.aboback.wanandroidjetpack.find.ui

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryActivity
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.find.viewmodel.TreeListViewModel

/**
 * @author jhb
 * @date 2020/11/17
 */
class TreeListActivity : BaseVMRepositoryActivity<TreeListViewModel>(R.layout.activity_tree_list) {

    override fun getViewModel(app: Application) = TreeListViewModel(app)


}