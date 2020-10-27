package com.aboback.wanandroidjetpack.network

import com.aboback.network.WanService

/**
 * @author jhb
 * @date 2020/10/23
 */
object WanServer {
    val api by lazy { WanService.create<ApiService>() }


}