package com.jhb.wanandroidjetpack.bean


data class ArticleListBean(
        var data: DataBean?
) : BaseBean() {

    data class DataBean(
            var curPage: Int?,
            var datas: List<DatasBean>?,
            var offset: Int?,
            var over: Boolean?,
            var pageCount: Int?,
            var size: Int?,
            var total: Int?
    ) {
        data class DatasBean(
                var apkLink: String?,
                var audit: Int?,
                var author: String?,
                var canEdit: Boolean?,
                var chapterId: Int?,
                var chapterName: String?,
                var collect: Boolean?,
                var courseId: Int?,
                var desc: String?,
                var descMd: String?,
                var envelopePic: String?,
                var fresh: Boolean?,
                var id: Int?,
                var link: String?,
                var niceDate: String?,
                var niceShareDate: String?,
                var origin: String?,
                var prefix: String?,
                var projectLink: String?,
                var publishTime: Long?,
                var selfVisible: Int?,
                var shareDate: Long?,
                var shareUser: String?,
                var superChapterId: Int?,
                var superChapterName: String?,
                var tags: List<Any?>?,
                var title: String?,
                var type: Int?,
                var userId: Int?,
                var visible: Int?,
                var zan: Int?
        )
    }
}