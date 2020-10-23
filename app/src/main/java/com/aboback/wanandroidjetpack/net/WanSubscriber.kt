package com.aboback.wanandroidjetpack.net

import com.google.gson.JsonParseException
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.base.viewmodel.BaseViewModel
import com.aboback.wanandroidjetpack.bean.BaseBean
import com.aboback.wanandroidjetpack.util.getResString
import com.aboback.wanandroidjetpack.util.logEWhitT
import io.reactivex.subscribers.ResourceSubscriber
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

/**
 * Created by jhb on 2020/3/24.
 */
abstract class WanSubscriber<T : BaseBean>(private val vm: BaseViewModel? = null) : ResourceSubscriber<T>() {

    companion object {
        const val TAG = "WanSubscriber"
    }

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
        "onStart  ${Thread.currentThread().name} ".logEWhitT(TAG)
        vm?.showLoadingDialog()
    }

    override fun onNext(t: T) {
        when (t.errorCode) {
            NetConstant.SUCCESS -> onSuccess(t)
            else -> onFail(t)
        }

    }


    override fun onError(e: Throwable?) {
        e?.printStackTrace()
        e?.message?.logEWhitT("$TAG ==> onError ")

        val errorMsg: String
        when (e) {
            is HttpException -> {
                errorMsg = when (e.code()) {
                    UNAUTHORIZED, FORBIDDEN, NOT_FOUND,
                    REQUEST_TIMEOUT, GATEWAY_TIMEOUT,
                    INTERNAL_SERVER_ERROR, BAD_GATEWAY,
                    SERVICE_UNAVAILABLE -> e.code().toString()
                    else -> R.string.network_error_unknown_code.getResString()

                }
            }
            is SocketTimeoutException -> {
                errorMsg = R.string.socket_time_out.getResString()
            }
            is JsonParseException, is JSONException, is ParseException -> {
                errorMsg = R.string.parse_data_error.getResString()
            }
            is ConnectException -> {
                errorMsg = R.string.server_http_error.getResString()
            }
            else -> {
                errorMsg = R.string.unknown_error.getResString() + " e = " + e?.message
            }
        }

        errorMsg.logEWhitT("$TAG ==> errorString ")

        onError(errorMsg)
    }


    override fun onComplete() {
        onFinish(true)

    }


    abstract fun onSuccess(t: T)

    open fun onFail(t: T) {
        vm?.showErrorDialog(t.errorMsg)


    }

    open fun onError(errorMsg: String) {
        onFinish(false)

        vm?.showErrorDialog("网络错误")
        vm?.dismissDialogDelay(1000)

    }

    open fun onFinish(isSuccess: Boolean) {
        vm?.dismissDialog()

    }

}