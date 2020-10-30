package com.aboback.wanandroidjetpack.db

import androidx.room.TypeConverter
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author jhb
 * @date 2020/6/4
 */
class WendaListBeanArrType {


    @TypeConverter
    fun stringToList(s: String?): ArrayList<ItemDatasBean>? =
            Gson().fromJson(s, object : TypeToken<ArrayList<ItemDatasBean>>() {}.type)

    @TypeConverter
    fun listToString(list: ArrayList<ItemDatasBean>?): String? = Gson().toJson(list)

}






