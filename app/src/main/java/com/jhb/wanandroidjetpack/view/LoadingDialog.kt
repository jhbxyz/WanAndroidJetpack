package com.jhb.wanandroidjetpack.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.databinding.DialogLoadingBinding
import com.jhb.wanandroidjetpack.util.delay

/**
 * Created by jhb on 2020/3/26.
 */
class LoadingDialog(private val activity: AppCompatActivity) : Dialog(activity, R.style.LoadingDialogTheme) {


    lateinit var mVM: LoadingDialogVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.inflate<DialogLoadingBinding>(LayoutInflater.from(context), R.layout.dialog_loading, null, false)

        mVM = ViewModelProvider(activity)[LoadingDialogVM::class.java]

        binding.setVariable(mVM.getVariableId(), mVM)
        setContentView(binding.root)
        setCanCancel(false)

    }

    fun setCanCancel(can: Boolean) {
        setCancelable(can)
    }

    private fun dialogShow() {
        if (!activity.isFinishing) {
            show()
        }
    }

    fun showLoading(content: String) {
        dialogShow()

        mVM.isProgress.set(true)
        mVM.tip.set(content)

    }


    fun showSuccess(content: String) {
        dialogShow()
        mVM.isProgress.set(false)
        mVM.icon.set(R.drawable.ico_pzlr_cg)
        mVM.tip.set(content)


    }

    fun showError(content: String) {
        dialogShow()
        mVM.isProgress.set(false)
        mVM.icon.set(R.drawable.ico_pzlr_sb)
        mVM.tip.set(content)

    }

    fun dialogDiss() {
        if (isShowing) {
            dismiss()
        }
    }

    fun dialogDissDelay(time: Int) {
        time.delay {
            if (isShowing) {
                dismiss()
            }
        }

    }

}