package com.neihanjetpack

import android.os.Bundle
import com.neihanjetpack.base.baseview.BaseActivity
import com.neihanjetpack.data.model.NeiHanData
import com.neihanjetpack.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }

    override fun initView(savedInstanceState: Bundle?) {
        getBindingView().data = NeiHanData(1, "文字")
    }


}
