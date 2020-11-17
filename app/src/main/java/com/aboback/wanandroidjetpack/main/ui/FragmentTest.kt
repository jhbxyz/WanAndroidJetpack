package com.aboback.wanandroidjetpack.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.aboback.base.ui.BaseFragment
import com.aboback.base.util.logWithTag
import com.aboback.wanandroidjetpack.collect.SelectPage

/**
 * Created by jhb on 2020-02-11.
 */
class FragmentTest(val page: Int) : BaseFragment(), SelectPage {
    constructor() : this(1)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        "this = $this   onCreateView  ".logWithTag(mTag)
        val textView = TextView(context)
        textView.text = javaClass.simpleName + "$page"
        return textView
    }

    override fun pageIndex() = -1

    //
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        "this = $this   onViewCreated ".logWithTag(mTag)
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        "this = $this   onResume ".logWithTag(mTag)
//
//    }
    override fun onSelectPage() {
        "onSelectPage = page = $page".logWithTag(mTag)

    }

//
//    override fun onDetach() {
//        super.onDetach()
//        "this = $this   onDetach ".logWithTag(mTag)
//
//    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        "this = $this   onDestroyView ".logWithTag(mTag)
//
//
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//        "this = $this   onDestroy ".logWithTag(mTag)
//
//    }
}