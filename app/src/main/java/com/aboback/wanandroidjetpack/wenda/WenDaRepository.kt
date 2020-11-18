package com.aboback.wanandroidjetpack.wenda

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class WenDaRepository : NetRepository() {

    suspend fun wendaList(page: Int) = api.wendaList(page)


}