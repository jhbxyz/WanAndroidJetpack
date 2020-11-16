package com.aboback.wanandroidjetpack.find

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class FindContentNaviRepository : NetRepository() {

    suspend fun naviList() = api.naviList()


}