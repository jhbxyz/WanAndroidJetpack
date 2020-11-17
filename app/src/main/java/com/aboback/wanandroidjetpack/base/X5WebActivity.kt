package com.aboback.wanandroidjetpack.base

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryActivity
import com.aboback.base.util.logWithTag
import com.aboback.base.util.truely
import com.aboback.base.view.LoadingDialog
import com.aboback.wanandroidjetpack.R
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_webview_x5.*
import java.util.concurrent.TimeUnit

/**
 * Created by jhb on 2019-08-06.
 */
class X5WebActivity : BaseVMRepositoryActivity<X5WebViewModel>(R.layout.activity_webview_x5) {

    companion object {
        private const val FILTER_URL_JIAN_SHU = "jianshu://notes/"
    }

    private val mDialog by lazy { LoadingDialog(this, true) }

    override fun getViewModel(app: Application): X5WebViewModel = X5WebViewModel(app)

    override fun beforeSetView() {
        super.beforeSetView()
//        window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    override fun onViewInit() {
        super.onViewInit()
        setWebView()
    }

    override fun onEvent() {
        super.onEvent()
        mRealVM.mScrollToTop.observe(this, Observer {
            if (it) {
                nsv.smoothScrollTo(0, 0, 800)
//                nsv.scrollTo(0, 0)
                mRealVM.mScrollToTop.value = false
            }
        })
    }

    private fun setWebView() {
        WebView.setWebContentsDebuggingEnabled(true)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.setAppCacheMaxSize((1024 * 1024 * 4).toLong())
        val appCachePath = applicationContext.cacheDir.absolutePath
        settings.setAppCachePath(appCachePath)
        settings.allowFileAccess = true
        settings.setAppCacheEnabled(true)

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(webview: WebView?, url: String?): Boolean {
                "shouldOverrideUrlLoading url = $url".logWithTag(mTag)
                return when {
                    url?.contains(FILTER_URL_JIAN_SHU).truely() -> {
                        true
                    }
                    else -> false
                }
            }

            override fun onPageFinished(webview: WebView?, url: String?) {
                super.onPageFinished(webview, url)
                "onPageFinished url = $url".logWithTag(mTag)
                hideDialog()
            }

            override fun onPageStarted(webview: WebView?, url: String?, bitmap: Bitmap?) {
                super.onPageStarted(webview, url, bitmap)
                "onPageStarted url = $url".logWithTag(mTag)

                showDialog()
            }
        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(webview: WebView?, progress: Int) {
                super.onProgressChanged(webview, progress)
                "onProgressChanged progress = $progress".logWithTag(mTag)
                if (progress >= 100) {
                    hideDialog()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onBackClick()
    }

    @SuppressLint("CheckResult")
    private fun onBackClick() {

        Observable.timer(200, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    if (!isFinishing) {
                        super.onBackPressed()
                    }
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()

        (webView.parent as ViewGroup).removeView(webView)
        webView.stopLoading()
        webView.loadUrl(null)
        webView.clearHistory()
        webView.removeAllViews()
        webView.pauseTimers()
        webView.clearCache(true)
        webView.settings.javaScriptEnabled = false
        webView.tag = null
        webView.destroy()
    }

}