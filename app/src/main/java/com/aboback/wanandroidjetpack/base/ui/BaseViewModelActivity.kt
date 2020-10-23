package com.aboback.wanandroidjetpack.base.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aboback.wanandroidjetpack.base.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.callback.GlobalSingle
import com.aboback.wanandroidjetpack.common.DialogState
import java.io.Serializable

/**
 * Created by jhb on 2020/3/19.
 */
open class BaseViewModelActivity<VM : BaseLayoutViewModel>(@LayoutRes private val layoutId: Int, private val clazz: Class<VM>) : BaseActivity(), IControllerEvent {

    lateinit var mBinding: ViewDataBinding
    lateinit var mRealVM: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetView()
        mRealVM = ViewModelProvider(this)[clazz]
        mBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, layoutId)
        mBinding.setVariable(mRealVM.getVariableId(), mRealVM)
        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        mustInit()
        onViewInit()

        mRealVM.onModelBind()

        onEvent()
    }



    override fun beforeSetView() {

    }

    override fun onViewInit() {

    }

    private fun mustInit() {

        mDialogStateObserver = Observer {
            when (it) {
                DialogState.LOADING -> {
                    showLoadingDialog(it.state)
                }
                DialogState.SUCCESS -> {
                    showSuccessDialog(it.state)
                }
                DialogState.ERROR -> {
                    showErrorDialog(it.state)

                }
                DialogState.DISMISS -> {
                    dismissDialog()

                }
                DialogState.DISMISS_DELAY -> {
                    dismissDialogDelay(it.state.toInt())

                }
            }
        }

        GlobalSingle.finishActivity.observe(this, Observer {
            finish()
        })

    }

    override fun onResume() {
        super.onResume()
        GlobalSingle.dialogState.observe(this, mDialogStateObserver)

    }

    override fun onPause() {
        super.onPause()
        GlobalSingle.dialogState.removeObserver(mDialogStateObserver)
    }

    private lateinit var mDialogStateObserver: Observer<DialogState>
    override fun onEvent() {

    }

    fun startActivity(clazz: Class<*>, vararg data: Pair<String, Any?>) {
        val intent = Intent(this, clazz)

        data.forEach {
            when (it.second) {
                is Boolean -> {
                    intent.putExtra(it.first, it.second as Boolean)
                }
                is Byte -> {
                    intent.putExtra(it.first, it.second as Byte)
                }
                is Int -> {
                    intent.putExtra(it.first, it.second as Int)
                }
                is Short -> {
                    intent.putExtra(it.first, it.second as Short)
                }
                is Long -> {
                    intent.putExtra(it.first, it.second as Long)
                }
                is Float -> {
                    intent.putExtra(it.first, it.second as Float)
                }
                is Double -> {
                    intent.putExtra(it.first, it.second as Double)
                }
                is Char -> {
                    intent.putExtra(it.first, it.second as Char)
                }
                is String -> {
                    intent.putExtra(it.first, it.second as String)
                }
                is Serializable -> {
                    intent.putExtra(it.first, it.second as Serializable)
                }
                is Parcelable -> {
                    intent.putExtra(it.first, it.second as Parcelable)
                }
            }
        }

        startActivity(intent)
    }


    fun showLoadingDialog(content: String = "正在加载...") {
        mDialog.showLoading(content)
    }

    fun showSuccessDialog(content: String = "加载成功") {
        mDialog.showSuccess(content)
    }

    fun showErrorDialog(content: String = "加载失败") {
        mDialog.showError(content)
    }

    fun dismissDialog() {
        mDialog.dialogDiss()
    }

    fun dismissDialogDelay(time: Int = 150) {
        mDialog.dialogDissDelay(time)
    }

}