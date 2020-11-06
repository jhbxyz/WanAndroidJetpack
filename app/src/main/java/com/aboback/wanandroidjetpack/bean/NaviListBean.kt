package com.aboback.wanandroidjetpack.bean

import com.aboback.network.BaseBean

/**
 * @author jhb
 * @date 2020/11/6
 */
data class NaviListBean(
        var data: List<Data>?
) : BaseBean() {
    data class Data(
            var articles: List<ItemDatasBean>?,
            var name: String?,
            var cid: Int?
    )
}