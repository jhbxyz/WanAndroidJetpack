package com.aboback.wanandroidjetpack.bean

import com.aboback.network.BaseBean

data class CoinUserInfoBean(
        val data: Data? = null
) : BaseBean() {
    data class Data(
            val coinCount: Int? = null,
            val level: Int? = null,
            val rank: String? = null,
            val userId: Int? = null,
            val username: String? = null
    )
}