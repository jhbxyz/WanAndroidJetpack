package com.aboback.base.viewmodel

import android.app.Application
import android.content.Intent
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.io.Serializable

/**
 * @author jhb
 * @date 2020/10/21
 */
open class BaseViewModel(app: Application) : AndroidViewModel(app) {

    var mTag = javaClass.simpleName

    var isDialogShow = MutableLiveData<Boolean>()

    open fun onModelBind() {

    }


    /**
     *  TODO tips: TaskId 是一样的，在同一个栈中 因为亲和性的原因
     *      原则是：设置此状态，首先会查找是否存在和被启动的Activity具有相同的亲和性的任务栈
     *      （即taskAffinity，注意同一个应用程序中的activity的亲和性一样），
     *      如果有，则直接把这个栈整体移动到前台，并保持栈中的状态不变，即栈中的activity顺序不变，
     *      如果没有，则新建一个栈来存放被启动的activity
     */
    fun startActivity(clazz: Class<*>, vararg data: Pair<String, Any?>) {
        val application = getApplication<Application>()

        val intent = Intent(application, clazz)

        data.forEach {
            when (it.second) {
                is Boolean -> {
                    intent.putExtra(it.first, it.second as Boolean)
                }
                is Byte -> {
                    intent.putExtra(it.first, it.second as Byte)
                }
                is Int -> {
                    intent.putExtra(it.first, it.second as Int)
                }
                is Short -> {
                    intent.putExtra(it.first, it.second as Short)
                }
                is Long -> {
                    intent.putExtra(it.first, it.second as Long)
                }
                is Float -> {
                    intent.putExtra(it.first, it.second as Float)
                }
                is Double -> {
                    intent.putExtra(it.first, it.second as Double)
                }
                is Char -> {
                    intent.putExtra(it.first, it.second as Char)
                }
                is String -> {
                    intent.putExtra(it.first, it.second as String)
                }
                is Serializable -> {
                    intent.putExtra(it.first, it.second as Serializable)
                }
                is Parcelable -> {
                    intent.putExtra(it.first, it.second as Parcelable)
                }
            }
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(intent)

    }

}