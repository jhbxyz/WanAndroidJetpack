package com.aboback.base.viewmodel

import android.app.Application
import com.aboback.base.BaseRepository

/**
 * @author jhb
 * @date 2020/10/23
 */
abstract class BaseRepositoryViewModel<T : BaseRepository>(app: Application, val mRepo: T) : BaseLayoutViewModel(app)