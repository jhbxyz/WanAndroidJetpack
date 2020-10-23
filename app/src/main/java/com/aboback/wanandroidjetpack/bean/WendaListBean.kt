package com.aboback.wanandroidjetpack.bean

import androidx.room.*
import com.aboback.wanandroidjetpack.db.WendaListBeanArrType
import java.io.Serializable


data class WendaListBean(
        var data: DataBean?
) : BaseBean() {

    @Entity(tableName = "WendaListBeanDataBean")
    @TypeConverters(WendaListBeanArrType::class)
    data class DataBean(
            @PrimaryKey(autoGenerate = true)
            var curPage: Int?,
            var datas: ArrayList<DatasBean>?,
            var mLastRequestTime: Long,//上次请求的时间
            var offset: Int?,
            var over: Boolean?,
            var pageCount: Int?,
            var size: Int?,
            var total: Int?) : Serializable {

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
//                @Ignore
//                var tags: List<TagBean?>?,
                var title: String?,
                var type: Int?,
                var userId: Int?,
                var visible: Int?,
                var zan: Int?) : Serializable {

//            data class TagBean(
//                    var name: String?,
//                    var url: String?) : Serializable
        }
    }
}