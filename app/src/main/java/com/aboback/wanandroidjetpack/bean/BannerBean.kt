package com.aboback.wanandroidjetpack.bean

data class BannerBean(val data: List<Data?>? = null) : BaseBean() {
    data class Data(
            val desc: String? = null,
            val id: Int? = null,
            val imagePath: String? = null,
            val isVisible: Int? = null,
            val order: Int? = null,
            val title: String? = null,
            val type: Int? = null,
            val url: String? = null
    )
}