package com.aboback.wanandroidjetpack.home

import com.aboback.base.BaseRepository
import com.aboback.wanandroidjetpack.net.WanService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class HomeRepository : BaseRepository {


    suspend fun articleListKt(page: Int) = withContext(Dispatchers.IO) {
        WanService.api.articleListKt(page)
    }


}