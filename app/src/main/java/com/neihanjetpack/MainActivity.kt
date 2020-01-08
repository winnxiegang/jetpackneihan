package com.neihanjetpack

import android.os.Bundle
import com.neihanjetpack.base.adapter.MyBaseDataBingAdapter
import com.neihanjetpack.base.baseview.BaseActivity
import com.neihanjetpack.base.http.ApiClient
import com.neihanjetpack.base.http.RxTransformer
import com.neihanjetpack.base.utils.RecyclerViewUtils
import com.neihanjetpack.data.entity.result.NeiHanResult
import com.neihanjetpack.data.model.NeiHanData
import com.neihanjetpack.databinding.ActivityMainBinding
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.trello.rxlifecycle2.android.ActivityEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>(), OnRefreshListener, OnLoadMoreListener {

    private val myBaseDataBingAdapter
            by lazy { MyBaseDataBingAdapter<NeiHanResult.ResultList>(R.layout.activity_neihan_list_item) }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }

    override fun initView(savedInstanceState: Bundle?) {
        getBindingView().data = NeiHanData(1, "文字")
        RecyclerViewUtils.initRecyclerView(mRecyclerView, myBaseDataBingAdapter)
        mRefreshLayout.setOnRefreshListener(this)
        mRefreshLayout.autoRefresh()
        mRefreshLayout.setOnLoadMoreListener(this)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        getCheckGoodDetal(1.toString(), "text")
        mRefreshLayout.finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
    }

    fun getCheckGoodDetal(page: String, type: String) {
        ApiClient.mApiServer.getData(page, type)
            .compose(RxTransformer.io2Main())
            .compose(bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe({
                myBaseDataBingAdapter.setNewData(it.result)
            }, {})
    }
}
