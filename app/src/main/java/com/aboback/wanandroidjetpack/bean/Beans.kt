package com.aboback.wanandroidjetpack.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aboback.network.BaseBean
import com.aboback.wanandroidjetpack.db.WendaListBeanArrType
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author jhb
 * @date 2020/10/26
 */

data class ItemDatasBean(
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
        var zan: Int?,
        val imagePath: String? = null,
        val isVisible: Int? = null,
        val order: Int? = null,
        val url: String? = null,
        val name: String? = null
) {
    data class TagBean(var name: String?, var url: String?)
}

data class ArrayDataBean(
        var data: List<ItemDatasBean>?
) : BaseBean()


data class ObjectDataBean(
        val coinInfo: CoinInfo? = null,
        @SerializedName(value = "data", alternate = ["shareArticles"])
        var data: DataBean? = null
) : BaseBean() {

    @Entity(tableName = "WendaListBeanDataBean")
    @TypeConverters(WendaListBeanArrType::class)
    data class DataBean(
            @PrimaryKey(autoGenerate = true)
            var curPage: Int?,
            var datas: ArrayList<ItemDatasBean>?,
            var mLastRequestTime: Long,//上次请求的时间
            var offset: Int?,
            var over: Boolean?,
            var pageCount: Int?,
            var size: Int?,
            var total: Int?) : Serializable

    data class CoinInfo(
            val coinCount: Int? = null,
            val level: Int? = null,
            val rank: String? = null,
            val userId: Int? = null,
            val username: String? = null
    )
}