package com.aboback.wanandroidjetpack.me

import com.aboback.network.util.MmkvUtil
import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.util.response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/11/23
 */
class SettingRepository : NetRepository() {

    private val dao = db.userLoginDao
    suspend fun userLogout() = withContext(Dispatchers.IO) {
        var result = false
        response(api.userLogout()) {
            updateDB()
            MmkvUtil.clearCookies()
            result = true
        }
        result
    }

    private fun updateDB() {
        dao.getUserInfo()?.let {
            it.mIsLogin = false
            dao.onUserLogout(it)
        }
    }


}