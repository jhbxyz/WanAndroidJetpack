package com.aboback.wanandroidjetpack.base

import io.reactivex.observers.DisposableObserver

/**
 * Created by jhb on 2020-01-16.
 */
open class SimpleObserver<T> : DisposableObserver<T>() {
    override fun onComplete() {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }

}