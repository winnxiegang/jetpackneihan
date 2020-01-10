package com.neihanjetpack.fragmrent


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.myjeptdemo.room.NeiHanLike

import com.neihanjetpack.R
import com.neihanjetpack.base.adapter.MyBaseDataBingAdapter
import com.neihanjetpack.base.utils.LogUtils
import com.neihanjetpack.base.utils.RecyclerViewUtils
import com.neihanjetpack.base.utils.ToastUtil
import com.neihanjetpack.data.entity.result.NeiHanResult
import com.neihanjetpack.data.viewmodel.NeiHanViewModel
import com.neihanjetpack.databinding.FragmentTextListItemBinding
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_text.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class TextFragment : Fragment(), OnRefreshListener, OnLoadMoreListener {
    private val myBaseDataBingAdapter
            by lazy {
                MyBaseDataBingAdapter<NeiHanResult.ResultList>(
                    R.layout.fragment_text_list_item,
                    mutableListOf(R.id.mLinearFavorite)
                )
            }
    private val neiHanViewModel by lazy { ViewModelProviders.of(this).get(NeiHanViewModel::class.java) }
    private var page = 1
    private var listLike = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        initData()
        ToastUtil.toast("onViewCreated")
    }

    private fun initData() {


        neiHanViewModel.liveData.observe(this, Observer {
            if (page == 1) {
                LogUtils.d("listLike${listLike.size}")
                it.result.forEach {
                    LogUtils.d("result${it.sid}")
                    if (listLike.size == 0) it.isLike = false else it.isLike = listLike.contains(it.sid)
                }
                myBaseDataBingAdapter.setNewData(it.result)
                mRefreshLayout.finishRefresh()
            } else {
                myBaseDataBingAdapter.addData(it.result)
                mRefreshLayout.finishLoadMore()
            }

        })

    }

    suspend fun getListLike(): List<String> = withContext(Dispatchers.Main) {
        neiHanViewModel.getAllWorldLiveData().observe(this@TextFragment, Observer<List<NeiHanLike>> {
            listLike.clear()// 每次判断需要删除一下
            it?.forEach {
                LogUtils.d("neiHanViewModel${it.sid}")
                listLike.add(it.sid.toString())
            }
        })
        listLike
    }

    private fun initView(savedInstanceState: Bundle?) {
        RecyclerViewUtils.initRecyclerView(mRecyclerView, myBaseDataBingAdapter)
        mRefreshLayout.setOnRefreshListener(this)
        mRefreshLayout.setOnLoadMoreListener(this)
        mRefreshLayout.autoRefresh()
        myBaseDataBingAdapter.setOnItemChildClickListener { _, view, position ->
            when (view.id) {
                R.id.mLinearFavorite -> {
                    myBaseDataBingAdapter.data[position].isLike = !myBaseDataBingAdapter.data[position].isLike
                    if (myBaseDataBingAdapter.data[position].isLike) {
                        if (!listLike.contains(myBaseDataBingAdapter.data[position].sid)) {
                            listLike.add(myBaseDataBingAdapter.data[position].sid)
                            neiHanViewModel.insertWorlds(
                                NeiHanLike(
                                    sid = myBaseDataBingAdapter.data[position].sid,
                                    text = myBaseDataBingAdapter.data[position].text,
                                    header = myBaseDataBingAdapter.data[position].header,
                                    name = myBaseDataBingAdapter.data[position].name,
                                    forward = myBaseDataBingAdapter.data[position].forward,
                                    up = myBaseDataBingAdapter.data[position].up,
                                    down = myBaseDataBingAdapter.data[position].down,
                                    comment = myBaseDataBingAdapter.data[position].comment
                                )
                            )
                        }
                    } else {
                        if (listLike.contains(myBaseDataBingAdapter.data[position].sid)) {
                            listLike.remove(myBaseDataBingAdapter.data[position].sid)
                            var neiHanLike = NeiHanLike(
                                sid = myBaseDataBingAdapter.data[position].sid,
                                text = myBaseDataBingAdapter.data[position].text,
                                header = myBaseDataBingAdapter.data[position].header,
                                name = myBaseDataBingAdapter.data[position].name,
                                forward = myBaseDataBingAdapter.data[position].forward,
                                up = myBaseDataBingAdapter.data[position].up,
                                down = myBaseDataBingAdapter.data[position].down,
                                comment = myBaseDataBingAdapter.data[position].comment
                            )
                            neiHanViewModel.deleteWorlds(
                                neiHanLike
                            )
                        }
                    }
                    myBaseDataBingAdapter.notifyItemChanged(position)
                }
            }

        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 1
        neiHanViewModel.getListData(page.toString(), "text")
        viewLifecycleOwner.lifecycleScope.launch {
            getListLike()
        }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        page++
        neiHanViewModel.getListData(page.toString(), "text")

    }

}
