package com.aboback.base.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.aboback.base.util.ActivityUtil
import com.aboback.base.view.LoadingDialog
import com.aboback.base.util.isNull
import com.aboback.base.util.logWithTag
import java.io.Serializable

/**
 * @author jhb
 * @date 2020/10/15
 */
open class BaseActivity : AppCompatActivity() {

    private var mDialog: LoadingDialog? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtil.addActivity(this)
    }

    private fun initDialog(): LoadingDialog {
        if (mDialog.isNull()) {
            mDialog = LoadingDialog(this)
        }
        return mDialog!!
    }

    fun showDialog() {
        initDialog()
        mDialog?.show()
    }

    fun hideDialog() {
        initDialog()
        mDialog?.dismiss()
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


    override fun onDestroy() {
        super.onDestroy()
        ActivityUtil.removeActivity(this)
    }
}