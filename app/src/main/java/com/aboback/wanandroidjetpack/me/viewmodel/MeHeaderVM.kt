package com.aboback.wanandroidjetpack.me.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.util.randomInt
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.login.ui.LoginActivity
import com.aboback.wanandroidjetpack.viewmodel.BannerViewModel

/**
 * @author jhb
 * @date 2020/10/27
 */
const val UN_LOGIN_PATH = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604293795821&di=ba72880ccaab5704043010c2d3494d06&imgtype=0&src=http%3A%2F%2Fzkres2.myzaker.com%2F202003%2F5e72abdc8e9f0956a37089fe_1024.jpg"
private const val PATH_1 = "https://images.unsplash.com/photo-1603977503292-79226279141c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
private const val PATH_2 = "https://images.unsplash.com/photo-1497752531616-c3afd9760a11?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1500&q=80"
private const val PATH_3 = "https://images.unsplash.com/photo-1555169062-013468b47731?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
private const val PATH_4 = "https://images.unsplash.com/photo-1530126483408-aa533e55bdb2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
private const val PATH_5 = "https://images.unsplash.com/photo-1496963729609-7d408fa580b5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1502&q=80"

class MeHeaderVM(app: Application) : BaseMultiItemViewModel(app) {

    private val imagePaths = arrayOf(PATH_1, PATH_2, PATH_3, PATH_4, PATH_5)
    val mUserName = ObservableField<String>()
    var mPath = ObservableField(UN_LOGIN_PATH)


    var mIdAndRank = ObservableField("")


    fun loadAvatar() {
        mPath.set(imagePaths[randomInt(imagePaths.size)])
    }


    fun login() {
        if (WanApp.isLogin) return
        startActivity(LoginActivity::class.java)
    }


    override val itemType: Int = ItemType.ITEM_ME_HEADER
}