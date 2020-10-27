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
            var datas: ArrayList<ArticleDatasBean>?,
            var mLastRequestTime: Long,//上次请求的时间
            var offset: Int?,
            var over: Boolean?,
            var pageCount: Int?,
            var size: Int?,
            var total: Int?) : Serializable
}