package com.aboback.wanandroidjetpack.base

import com.aboback.base.BaseRepository
import com.aboback.network.WanService
import com.aboback.wanandroidjetpack.network.ApiService

/**
 * @author jhb
 * @date 2020/10/23
 */
open class NetRepository : BaseRepository {
    val api by lazy { WanService.create<ApiService>() }

}