package com.aboback.wanandroidjetpack.bean

import com.aboback.network.BaseBean

data class WeChatListBean(
        val data: List<Data?>? = null
): BaseBean() {
    data class Data(
            val children: List<Any?>? = null,
            val courseId: Int? = null,
            val id: Int? = null,
            val name: String? = null,
            val order: Int? = null,
            val parentChapterId: Int? = null,
            val userControlSetTop: Boolean? = null,
            val visible: Int? = null
    )
}