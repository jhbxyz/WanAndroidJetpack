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
const val UN_LOGIN_PATH = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3386352505,2412195498&fm=26&gp=0.jpg"
//星爷
private const val PATH_1 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=828005745,3289366977&fm=26&gp=0.jpg"
//不良帅
private const val PATH_2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605692002440&di=11066523c1aec02404b0fcf1f725b544&imgtype=0&src=http%3A%2F%2Fstatic.1sapp.com%2Fqupost%2Fimages%2F2019%2F09%2F05%2F1567694723169848013.jpg"
//刘亦菲
private const val PATH_3 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2181944231,3260797126&fm=26&gp=0.jpg"
//卡通少女
private const val PATH_4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605785704348&di=e43529d0ac9320d51d99a5ffa364abc0&imgtype=0&src=http%3A%2F%2Fi1.sinaimg.cn%2Fdongman%2Fpictcol%2F2007-11-20%2FU2319P55T4D134846F50DT20071120174337_600small.jpg"

class MeHeaderVM(app: Application) : BaseMultiItemViewModel(app) {

    private val imagePaths = arrayOf(PATH_1, PATH_2, PATH_3, PATH_4)
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