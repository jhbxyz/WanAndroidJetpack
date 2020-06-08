package com.jhb.wanandroidjetpack.base

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.ui.BaseActivity
import com.jhb.wanandroidjetpack.util.logE
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
class X5WebActivity : BaseActivity() {

    var url = ""
    var title = ""
    private val TAG = "X5WebActivity"

    companion object {
        private const val WEB_URL = "web_url"
        private const val WEB_TITLE = "web_title"

        fun startActivity(url: String?, title: String? = null) {
            val intent = Intent(WanApp.instance, X5WebActivity::class.java)
            intent.putExtra(WEB_URL, url)
            intent.putExtra(WEB_TITLE, title)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            WanApp.instance.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_webview_x5)
        "$TAG   onCreate".logE()



        initView()

        initEvent()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        "$TAG   onConfigurationChanged".logE()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        "$TAG   onNewIntent".logE()

    }

    private fun initView() {

        title = intent.getStringExtra(WEB_TITLE) ?: ""


        url = intent.getStringExtra(WEB_URL) ?: ""

        "WanWebActivity url = $url".logE()

    }

    private fun initEvent() {


    }


    override fun onResume() {
        super.onResume()
        setWebView()
        webView.loadUrl(url)
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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        }
        webView.webViewClient = object : WebViewClient() {}
        webView.webChromeClient = object : WebChromeClient() {}


    }

    override fun onBackPressed() {
        super.onBackPressed()
//        onBackClick()
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
        "$TAG   onDestroy".logE()

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