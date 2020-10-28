package com.aboback.base.rv

import android.app.Application
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author jhb
 * @date 2020/10/27
 */
abstract class BaseMultiItemViewModel(app: Application) : QuickItemViewModel(app), MultiItemEntity {

}