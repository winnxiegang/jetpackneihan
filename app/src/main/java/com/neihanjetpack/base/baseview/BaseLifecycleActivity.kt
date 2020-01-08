package com.neihanjetpack.base.baseview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import com.neihanjetpack.MyApplication
import com.neihanjetpack.R
import com.neihanjetpack.base.utils.LogUtils
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

open class BaseLifecycleActivity : RxAppCompatActivity(), LifecycleOwner {
    private lateinit var mLifecycleRegistry: LifecycleRegistry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //创建Lifecycle对象
        mLifecycleRegistry = LifecycleRegistry(this)
        //标记状态
        mLifecycleRegistry.currentState = Lifecycle.State.CREATED
        LogUtils.d("Lifecycle.State.CREATED")
        MyApplication.getRefWatcher().watch(this)
        mLifecycleRegistry.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            }

        })
    }

    override fun getLifecycle(): Lifecycle {
        return super.getLifecycle()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        super.onResume()
        mLifecycleRegistry.currentState = Lifecycle.State.RESUMED
        LogUtils.d("Lifecycle.State.RESUMED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        super.onPause()
        LogUtils.d("Lifecycle.State.ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        super.onStart()
        mLifecycleRegistry.currentState = Lifecycle.State.STARTED
        LogUtils.d("Lifecycle.State.STARTED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
        super.onStop()
        LogUtils.d("Lifecycle.State.ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        super.onDestroy()
        mLifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        LogUtils.d("Lifecycle.State.DESTROYED")

    }
}
