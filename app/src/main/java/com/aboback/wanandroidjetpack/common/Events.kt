package com.aboback.wanandroidjetpack.common

import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.view.EditPage
import java.io.Serializable

/**
 * @author jhb
 * @date 2020/11/13
 */
data class EditDialogEventBean(var id: Int?, var name: String?, var link: String?)

data class EditDialogEvent(var page: EditPage, var bean: EditDialogEventBean? = null, var collectContentPage: CollectContentPage)

data class CommonItemBean(var id: Int?, var title: String?, var link: String?, var collect: Boolean) : Serializable



