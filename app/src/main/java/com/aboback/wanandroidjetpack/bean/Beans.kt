package com.aboback.wanandroidjetpack.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aboback.network.BaseBean
import com.aboback.wanandroidjetpack.db.convert.BannerDataListTypeConverter
import com.aboback.wanandroidjetpack.db.convert.ItemDatasListTypeConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author jhb
 * @date 2020/10/26
 */

@Entity(tableName = "BannerBean")
@TypeConverters(BannerDataListTypeConverter::class)
data class BannerDataBean(
        @PrimaryKey
        var index: Int,
        var data: List<Data>?,
        var mLastTime: Long = System.currentTimeMillis()
) : BaseBean() {
    data class Data(
            var desc: String?,
            var id: Int?,
            var isVisible: Int?,
            var order: Int?,
            var type: Int?,
            var imagePath: String?,
            var title: String?,
            var url: String?
    )
}

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
        var originId: Int?,
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
        val name: String? = null,
        //=====coin rank=========
        val coinCount: Int? = null,
        val level: Int? = null,
        val rank: String? = null,
        val username: String? = null
        //=====coin rank=========

) {
    data class TagBean(var name: String?, var url: String? = null)
}

@Entity(tableName = "ArrayDataBean")
@TypeConverters(ItemDatasListTypeConverter::class)
data class ArrayDataBean(
        @PrimaryKey
        var index: Int,
        var data: List<ItemDatasBean>?,
        var mLastTime: Long = System.currentTimeMillis()
) : BaseBean()


data class ObjectDataBean(
        @SerializedName(value = "data", alternate = ["shareArticles"])
        var data: DataBean? = null
) : BaseBean() {

    @Entity(tableName = "ObjectDataBean_DataBean")
    @TypeConverters(ItemDatasListTypeConverter::class)
    data class DataBean(
            @PrimaryKey(autoGenerate = true)
            var curPage: Int?,
            var datas: List<ItemDatasBean>?,
            var offset: Int?,
            var over: Boolean?,
            var pageCount: Int?,
            var size: Int?,
            var total: Int?,

            var mLastTime: Long = System.currentTimeMillis()

    ) : Serializable
}