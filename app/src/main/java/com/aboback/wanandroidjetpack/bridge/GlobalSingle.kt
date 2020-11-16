package com.aboback.wanandroidjetpack.bridge

import androidx.lifecycle.MutableLiveData
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.common.EditDialogEvent
import com.aboback.wanandroidjetpack.util.CollectChangeBean

/**
 * @author jhb
 * @date 2020/11/2
 */
object GlobalSingle {

    val isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccessToCollect = MutableLiveData<CollectContentPage>()

    var onCollectChange = MutableLiveData<CollectChangeBean>()

    var onAddCollectWebsite = MutableLiveData<Boolean>()
    var onAddCollectArticle = MutableLiveData<CollectContentPage>()
    var onAddShareArticle = MutableLiveData<CollectContentPage>()

    var showEditDialog = MutableLiveData<EditDialogEvent>()

    var onFindPageSelect = MutableLiveData<Int>()


}