package com.aboback.wanandroidjetpack.db

import android.annotation.SuppressLint
import com.aboback.wanandroidjetpack.bean.WendaListBean
import com.aboback.wanandroidjetpack.util.WanExecutors
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author jhb
 * @date 2020/6/4
 */
object WenDaListManger {

    private val mDB by lazy { WanDatabase.getInstance() }

    private val mDao by lazy { mDB.wenDaListDao }

    fun insertDataBean(datas: WendaListBean.DataBean) {
        WanExecutors.mDiskIO.execute {
            mDao.insertDataBean(datas)

        }

    }


    @SuppressLint("CheckResult")
    fun getDataBean(action: (WendaListBean.DataBean?) -> Unit) {
        Observable.create<WendaListBean.DataBean?> { emitter ->
            val dataBean = mDao.getDataBean()
            if (dataBean == null) {
                emitter.onError(Throwable("dataBean == null"))
            } else {
                emitter.onNext(dataBean)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                action.invoke(it)
            }, {
                action.invoke(null)

            })
    }

}