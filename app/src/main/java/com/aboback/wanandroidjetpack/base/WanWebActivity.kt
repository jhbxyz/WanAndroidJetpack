package com.aboback.wanandroidjetpack.base

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.aboback.base.BaseApp
import com.aboback.base.util.log
import com.aboback.base.ui.BaseActivity
import com.aboback.wanandroidjetpack.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_webview.*
import java.util.concurrent.TimeUnit

/**
 * Created by jhb on 2019-08-06.
 */
class WanWebActivity : BaseActivity() {

    var url = ""
    var title = ""
    private val TAG = this.javaClass.simpleName

    companion object {
        private const val WEB_URL = "web_url"
        private const val WEB_TITLE = "web_title"

        fun startActivity(url: String?, title: String? = null) {
            val intent = Intent(BaseApp.instance, WanWebActivity::class.java)
            intent.putExtra(WEB_URL, url)
            intent.putExtra(WEB_TITLE, title)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            BaseApp.instance.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_webview)

        initView()

        initEvent()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        TAG + "   onConfigurationChanged".log()
    }

    private fun initView() {

        title = intent.getStringExtra(WEB_TITLE) ?: ""


        url = intent.getStringExtra(WEB_URL) ?: ""

        "WanWebActivity url = $url".log()

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
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webView.webViewClient = object : WebViewClient() {

        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
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
//        (webView.parent as ViewGroup).removeView(webView)
//        webView.stopLoading()
//        webView.loadUrl(null)
//        webView.clearHistory()
//        webView.removeAllViews()
//        webView.pauseTimers()
//        webView.clearCache(true)
//        webView.settings.javaScriptEnabled = false
//        webView.tag = null
//        webView.destroy()
    }
}