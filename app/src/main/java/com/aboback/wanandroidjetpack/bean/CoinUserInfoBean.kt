package com.aboback.wanandroidjetpack.bean

import com.aboback.network.BaseBean
import java.io.Serializable

data class CoinUserInfoBean(
        val data: Data? = null
) : BaseBean(), Serializable {
    data class Data(
            val coinCount: Int? = null,
            val level: Int? = null,
            val rank: String? = null,
            val userId: Int? = null,
            val username: String? = null
    ) : Serializable
}