package com.aboback.wanandroidjetpack.me.ui

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryActivity
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.me.viewmodel.CoinRankViewModel

/**
 * @author jhb
 * @date 2020/11/11
 */
class CoinRankActivity : BaseVMRepositoryActivity<CoinRankViewModel>(R.layout.activity_coin_rank) {

    override fun getViewModel(app: Application) = CoinRankViewModel(app)


}