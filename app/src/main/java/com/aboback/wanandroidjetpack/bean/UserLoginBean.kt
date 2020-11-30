package com.aboback.wanandroidjetpack.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aboback.network.BaseBean
import com.aboback.wanandroidjetpack.db.convert.IntListTypeConverter

data class UserLoginBean(
        var data: Data? = null
) : BaseBean() {
    @Entity(tableName = "UserLoginData")
    @TypeConverters(value = [IntListTypeConverter::class])
    data class Data(
            var admin: Boolean? = null,
            @Ignore
            var chapterTops: List<Any?>? = null,
            var coinCount: Int? = null,
            var collectIds: List<Int>? = null,
            var email: String? = null,
            var icon: String? = null,
            @PrimaryKey
            var id: Int? = null,
            var nickname: String? = null,
            var password: String? = null,
            var publicName: String? = null,
            var token: String? = null,
            var type: Int? = null,
            var username: String? = null,

            var mIsLogin: Boolean = false,
            var mLoginTime: Long = System.currentTimeMillis()
    )
}