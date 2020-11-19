package com.aboback.wanandroidjetpack.me.viewmodel

import android.app.Application
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.databinding.ObservableField
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.base.X5WebActivity
import com.aboback.wanandroidjetpack.base.X5WebViewModel
import com.aboback.wanandroidjetpack.common.CommonItemBean
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel

/**
 * @author jhb
 * @date 2020/11/11
 */
class AboutMeViewModel(app: Application) : BaseLayoutViewModel(app) {


    var mTitleVM = TitleViewModel(
            leftAction = {
                finish()
            },
            title = "关于我"
    )

    companion object {

        private const val GITHUB = "https://github.com/jhbxyz"
        private const val JUE_JIN = "https://juejin.im/user/1574156381208216/posts"
    }

    private var contentString = """
        很高兴见到你！


        项目采用了，Kotlin + MVVM + Jetpack + Retrofit + Glide。

        基于 MVVM 架构，用 Jetpack 实现，网络采用 Kotlin 的协程和 Retrofit 配合使用！精美的 UI，便捷突出的功能！

        GitHub：$GITHUB

        掘金：$JUE_JIN


        如果遇到任何问题，欢迎联系我！

        jhbxyz@163.com

        我跟你一样在努力学习中，欢迎一起交流学习!

    """.trimIndent()
    var mContent = ObservableField<CharSequence>()

    override fun onModelBind() {
        super.onModelBind()
        mContent.set(initString(contentString))

    }

    private fun initString(content: String): SpannableString {
        val spanString = SpannableString(content)
        spanString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(X5WebActivity::class.java,
                        X5WebViewModel.FLAG_BEAN to CommonItemBean(-1, "GitHub", GITHUB, false),
                        X5WebViewModel.FLAG_SHOW_COLLECT_ICON to false
                )
            }
            //126+2+2+2
        }, 133, 165, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(X5WebActivity::class.java,
                        X5WebViewModel.FLAG_BEAN to CommonItemBean(-1, "掘金", JUE_JIN, false),
                        X5WebViewModel.FLAG_SHOW_COLLECT_ICON to false
                )
            }
        }, 167, 215, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spanString
    }

}