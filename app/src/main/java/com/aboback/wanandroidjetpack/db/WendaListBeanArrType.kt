package com.aboback.wanandroidjetpack.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.aboback.wanandroidjetpack.bean.WendaListBean

/**
 * @author jhb
 * @date 2020/6/4
 */
class WendaListBeanArrType {


    @TypeConverter
    fun stringToList(s: String?): ArrayList<WendaListBean.DataBean.DatasBean>? =
            Gson().fromJson(s, object : TypeToken<ArrayList<WendaListBean.DataBean.DatasBean>>() {}.type)

    @TypeConverter
    fun listToString(list: ArrayList<WendaListBean.DataBean.DatasBean>?): String? = Gson().toJson(list)

}






