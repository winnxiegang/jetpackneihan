package com.neihanjetpack.base.baseview

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<T : ViewDataBinding> : BaseLifecycleActivity() {
    /**
     * binding ,用于子类继承
     */
    protected var mBinding: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getContentLayoutId())
        if (mBinding == null) {//兼容xml 未绑定数据
            setContentView(getContentLayoutId())
        } else {
            setContentView(mBinding?.root)
            mBinding?.lifecycleOwner = this
        }
        initView(savedInstanceState)
        initData()
    }

    /*
       初始化布局
     */
    protected abstract fun initView(savedInstanceState: Bundle?)

    protected abstract fun initData()
    /**
     * 返回子类的 layoutId
     *
     * @return
     */
    protected abstract fun getContentLayoutId(): Int


    /**
     * 返回绑定的ViewDataBinding
     *
     * @return
     */
    fun getBindingView(): T {
        return mBinding!!
    }
}
