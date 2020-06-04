package com.jhb.wanandroidjetpack.callback

import com.jhb.wanandroidjetpack.bridge.UnPeekLiveData
import com.jhb.wanandroidjetpack.common.DialogState

/**
 * Created by jhb on 2020/3/23.
 */
object GlobalSingle {


    val loadingDialogState = UnPeekLiveData<Boolean>() //请求网络,开启loading

    val homeNavClick = UnPeekLiveData<Int>()

    var dialogState = UnPeekLiveData<DialogState>()

    var finishActivity = UnPeekLiveData<Unit>()


    var homeChangePage = UnPeekLiveData<Int>()


    var initHomeCards = UnPeekLiveData<Boolean>()

    var onChangeCompany = UnPeekLiveData<Boolean>()

    //发票夹 点击 各个筛选项的tab
    var onInvoiceTabClickPosition = UnPeekLiveData<Int>()
    var onClickInvoiceTabContentItem = UnPeekLiveData<Int>()

    //点击发票类型确定
    var onClickInvoiceTypeConfirm = UnPeekLiveData<Boolean>()


}