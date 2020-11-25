package com.aboback.wanandroidjetpack.db.convert

import androidx.room.TypeConverter
import com.aboback.wanandroidjetpack.bean.BannerDataBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author jhb
 * @date 2020/6/4
 */
class BannerDataListTypeConverter {


    @TypeConverter
    fun stringToList(s: String?): List<BannerDataBean.Data>? =
            Gson().fromJson(s, object : TypeToken<List<BannerDataBean.Data>>() {}.type)

    @TypeConverter
    fun listToString(list: List<BannerDataBean.Data>?): String? = Gson().toJson(list)

}






