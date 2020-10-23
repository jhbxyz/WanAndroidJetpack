package com.aboback

/**
 * @author jhb
 * @date 2020/10/22
 */
interface ViewState {

    fun beforeSetView()

    fun onViewInit()

    fun onEvent()

}