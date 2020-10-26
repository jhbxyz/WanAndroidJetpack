package com.aboback.wanandroidjetpack.bean

/**
 * @author jhb
 * @date 2020/10/26
 */
data class ArticleDatasBean(
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
        var tags: List<TagBean>?,
        var title: String?,
        var type: Int?,
        var userId: Int?,
        var visible: Int?,
        var zan: Int?
) {
    data class TagBean(var name: String?, var url: String?)
}

data class ArticleListBean(
        var data: DataBean?
) : BaseBean() {

    data class DataBean(
            var curPage: Int?,
            var datas: List<ArticleDatasBean>?,
            var offset: Int?,
            var over: Boolean?,
            var pageCount: Int?,
            var size: Int?,
            var total: Int?
    )
}

data class ArticleTopBean(
        var data: List<ArticleDatasBean>?
) : BaseBean()