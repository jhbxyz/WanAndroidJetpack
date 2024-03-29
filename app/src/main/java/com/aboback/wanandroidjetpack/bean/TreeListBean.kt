package com.aboback.wanandroidjetpack.bean

import com.aboback.network.BaseBean

data class TreeListBean(
        var data: List<DataBean?>?
) : BaseBean() {
    data class DataBean(
            var children: List<ChildrenBean?>?,
            var courseId: Int?,
            var id: Int?,
            var name: String?,
            var order: Int?,
            var parentChapterId: Int?,
            var userControlSetTop: Boolean?,
            var visible: Int?
    ) {
        data class ChildrenBean(
                var children: List<Any?>?,
                var courseId: Int?,
                var id: Int?,
                var name: String?,
                var order: Int?,
                var parentChapterId: Int?,
                var userControlSetTop: Boolean?,
                var visible: Int?
        )
    }
}