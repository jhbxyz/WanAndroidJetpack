package com.jhb.wanandroidjetpack.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Created by jhb on 2020-02-11.
 */
class Fragment(val page: Int) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val textView = TextView(context)
        textView.text = javaClass.simpleName + "$page"
        return textView
    }
}