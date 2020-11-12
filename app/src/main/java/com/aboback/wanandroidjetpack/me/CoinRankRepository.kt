package com.aboback.wanandroidjetpack.me

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/11/11
 */
class CoinRankRepository : NetRepository() {


    suspend fun coinRankList(page: Int) = withContext(Dispatchers.IO) {
        api.coinRankList(page)
    }

}