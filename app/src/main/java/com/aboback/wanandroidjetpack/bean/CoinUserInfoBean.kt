package com.aboback.wanandroidjetpack.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aboback.network.BaseBean
import java.io.Serializable

data class CoinUserInfoBean(
        val data: Data? = null
) : BaseBean(), Serializable {
    @Entity(tableName = "CoinUserInfoData")
    data class Data(
            val coinCount: Int? = null,
            val level: Int? = null,
            val rank: String? = null,
            @PrimaryKey
            val userId: Int? = null,
            val username: String? = null,

            var mNikeName: String? = null,
            var mLastTime: Long = System.currentTimeMillis()


    ) : Serializable
}