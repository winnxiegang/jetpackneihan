package com.neihanjetpack

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.WindowManager
import com.neihanjetpack.base.utils.UIUtil
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import org.litepal.LitePal


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2020/1/7
 * @Description:
 */

class MyApplication : Application() {


    companion object {
        /**
         * Logger日志全局Tag
         */
        private lateinit var mMyApplication: MyApplication
        private var mMainThreadId: Long = 0
        private lateinit var mMainThreadHandler: HandlerProxy
        private lateinit var mRefWatcher: RefWatcher
        /**
         * 获取Application Context
         */
        fun getInstance() = mMyApplication

        /**
         * 获取LeakCanary用来监听内存泄漏的对象
         */
        fun getRefWatcher() = mRefWatcher

        /**
         * 获取主线程id
         */
        fun getMainThreadId() = mMainThreadId

        /**
         * 获取主线程handler
         */
        fun getMainThreadHandler() = mMainThreadHandler


    }

    init {
        //设置全局的Header构建器
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, R.color.colorAccent) //全局设置主题颜色
            //MD样式
            MaterialHeader(context).setColorSchemeColors(UIUtil.getColor(R.color.colorAccent))
        }
        //设置全局的Footer构建器
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter(context).setDrawableSize(20f)
        }
    }

    //fix Toast$TN.handleShow
    class HandlerProxy(private val mHandler: Handler) : Handler() {

        override fun handleMessage(msg: Message) {
            try {
                mHandler.handleMessage(msg)
            } catch (e: WindowManager.BadTokenException) {
                //ignore
            }

        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //主动加载非主dex
    }

    override fun onCreate() {
        super.onCreate()
        mMyApplication = this
        mMainThreadId = Thread.currentThread().id
        mMainThreadHandler = HandlerProxy(Handler())
        initLeakCanary()
        LitePal.initialize(this)
    }

    /**
     * LeakCanary
     */
    private fun initLeakCanary() {
        //LeakCanary.install()会返回一个预定义的 RefWatcher，同时也会启用一个ActivityRefWatcher，用于自动监控调用
        //Activity.onDestroy()之后泄露的activity，如需在Fragment中监控，可在Fragment.onDestroy中使用refWatcher.watch()
        mRefWatcher = LeakCanary.install(getInstance())
    }

}