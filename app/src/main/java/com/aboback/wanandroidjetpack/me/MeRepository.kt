package com.aboback.wanandroidjetpack.me

import com.aboback.base.util.isNull
import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.util.response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/11/23
 */
class MeRepository : NetRepository() {

    private val dao = db.coinUserInfoDao
    suspend fun coinUserInfo() = withContext(Dispatchers.IO) {
        var coinUserInfo = dao.getCoinUserInfo(WanApp.userId)
        if (coinUserInfo.isNull() || coinUserInfo?.mLastTime.shouldUpdate()) {
            response(api.coinUserInfo()) {
                coinUserInfo = data
                coinUserInfo?.mNikeName = WanApp.nikeName
                dao.insert(coinUserInfo)
            }
        }
        coinUserInfo
    }


}