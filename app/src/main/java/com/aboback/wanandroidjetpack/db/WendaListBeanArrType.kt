package com.aboback.wanandroidjetpack.db

import androidx.room.TypeConverter
import com.aboback.wanandroidjetpack.bean.ArticleDatasBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.aboback.wanandroidjetpack.bean.WendaListBean

/**
 * @author jhb
 * @date 2020/6/4
 */
class WendaListBeanArrType {


    @TypeConverter
    fun stringToList(s: String?): ArrayList<ArticleDatasBean>? =
            Gson().fromJson(s, object : TypeToken<ArrayList<ArticleDatasBean>>() {}.type)

    @TypeConverter
    fun listToString(list: ArrayList<ArticleDatasBean>?): String? = Gson().toJson(list)

}






