package com.jhb.wanandroidjetpack.net

import com.google.gson.JsonParseException
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.util.LoadingDialog
import com.jhb.wanandroidjetpack.util.getResString
import com.jhb.wanandroidjetpack.util.logE
import com.jhb.wanandroidjetpack.util.showToast
import io.reactivex.observers.DisposableObserver
import io.reactivex.subscribers.DisposableSubscriber
import io.reactivex.subscribers.ResourceSubscriber
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

/**
 * Created by jhb on 2020-01-13.
 */
abstract class WanObserver<T : BaseBean> : DisposableObserver<T>() {
    //对应HTTP的状态码
    private val UNAUTHORIZED = 401
    private val FORBIDDEN = 403
    private val NOT_FOUND = 404
    private val REQUEST_TIMEOUT = 408
    private val INTERNAL_SERVER_ERROR = 500
    private val BAD_GATEWAY = 502
    private val SERVICE_UNAVAILABLE = 503
    private val GATEWAY_TIMEOUT = 504

    override fun onStart() {
        super.onStart()

    }

    override fun onNext(t: T) {
        when (t.errorCode) {
            NetConstant.SUCCESS -> {
                onSuccess(t)
            }
            else -> onFailed(t)
        }

    }

    override fun onError(e: Throwable) {

        "onError  ${e?.message}".logE()

        val exception = e

        val errorString: String
        if (exception is HttpException) {
            errorString = when (exception.code()) {
                UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT,
                INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> exception.code().toString()
                else -> R.string.network_error_unknown_code.getResString()

            }
        } else if (exception is SocketTimeoutException) {
            errorString = R.string.socket_time_out.getResString()
        } else if (exception is JsonParseException || exception is JSONException || exception is ParseException) {
            errorString = R.string.parse_data_error.getResString()
        } else if (exception is ConnectException) {
            errorString = R.string.server_http_error.getResString()
        } else {
            errorString = R.string.unknown_error.getResString() + " e = " + e?.message
        }

        "请求出错  errorString = $errorString".showToast()
    }


    override fun onComplete() {
        onFinish()
    }

    abstract fun onSuccess(t: T)


    fun onFailed(t: T) {
        t.errorMsg.showToast()
    }


    fun onFinish() {

    }


}