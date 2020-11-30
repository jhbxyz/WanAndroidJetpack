package com.aboback.wanandroidjetpack.login

import com.aboback.base.util.showToast
import com.aboback.network.NetConstant
import com.aboback.network.util.MmkvUtil
import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/11/24
 */
class LoginRepository : NetRepository() {

    val dao = db.userLoginDao

    suspend fun userLogin(username: String?, password: String?) = withContext(Dispatchers.IO) {
        var result = false
        api.userLogin(username, password)?.apply {
            val body = body()
            when (body?.errorCode) {
                NetConstant.SUCCESS -> {
                    val cookieSet = HashSet<String>()
                    headers().forEach { header ->
                        if (header.first == "Set-Cookie") {
                            cookieSet.add(header.second)
                        }
                    }
                    MmkvUtil.saveCookie(cookieSet)
                    val data = body.data
                    MmkvUtil.saveNikeName(data?.nickname ?: data?.publicName ?: data?.username ?: "")
                    WanApp.nikeName = data?.nickname
                    data?.mIsLogin = true
                    data?.mLoginTime = System.currentTimeMillis()
                    dao.insertUser(data)


                    result = true
                }
                else -> {
                    "${body?.errorMsg}".showToast()
                    result = false

                }
            }
        }
        result
    }
}