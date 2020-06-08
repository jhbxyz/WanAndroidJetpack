package com.jhb.wanandroidjetpack.util

import android.app.Activity
import java.lang.IllegalArgumentException

/**
 * Created by jhb on 2020-01-16.
 */
object ActivityUtil {

    var mActivities = arrayListOf<Activity>()

    fun addActivity(activity: Activity) {
        if (!mActivities.contains(activity)) {
            mActivities.add(activity)
        }
    }

    fun removeActivity(activity: Activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity)
        }
    }

    fun getStackTopAct(): Activity {
        if (mActivities.isEmpty()) {
            throw  IllegalArgumentException("can't get Activity ")
        }
        return mActivities[mActivities.size - 1]
    }

    fun finishAllActivity() {
        mActivities.forEach {
            if (!it.isFinishing) {
                it.finish()
            }
        }
    }


}