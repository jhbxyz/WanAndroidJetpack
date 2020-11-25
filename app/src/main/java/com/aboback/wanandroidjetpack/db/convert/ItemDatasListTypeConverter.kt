package com.aboback.wanandroidjetpack.db.convert

import androidx.room.TypeConverter
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author jhb
 * @date 2020/6/4
 */
class ItemDatasListTypeConverter {


    @TypeConverter
    fun stringToList(s: String?): List<ItemDatasBean>? =
            Gson().fromJson(s, object : TypeToken<List<ItemDatasBean>>() {}.type)

    @TypeConverter
    fun listToString(list: List<ItemDatasBean>?): String? = Gson().toJson(list)

}






