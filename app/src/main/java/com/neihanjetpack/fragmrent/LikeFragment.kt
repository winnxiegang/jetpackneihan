package com.neihanjetpack.fragmrent


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myjeptdemo.room.NeiHanLike
import com.neihanjetpack.R
import com.neihanjetpack.base.adapter.MyBaseDataBingAdapter
import com.neihanjetpack.base.utils.LogUtils
import com.neihanjetpack.base.utils.RecyclerViewUtils
import com.neihanjetpack.base.utils.ToastUtil
import com.neihanjetpack.data.viewmodel.NeiHanViewModel
import kotlinx.android.synthetic.main.fragment_like.*

/**
 * A simple [Fragment] subclass.
 */
class LikeFragment : Fragment() {
    private var mRootView: View? = null
    private val myBaseDataBingAdapter
            by lazy {
                MyBaseDataBingAdapter<NeiHanLike>(
                    R.layout.fragment_like_list_item,
                    mutableListOf(R.id.mLinearFavorite)
                )
            }
    private val neiHanViewModel by lazy { ViewModelProviders.of(this).get(NeiHanViewModel::class.java) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_like, container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        initData()
        ToastUtil.toast("onViewCreated")
    }

    private fun initData() {
        neiHanViewModel.getAllWorldLiveData().observe(this, Observer {
            it?.apply {
                myBaseDataBingAdapter.setNewData(this)
                LogUtils.d("neiHanViewModel${myBaseDataBingAdapter.data.size}")
            }


        })
    }

    private fun initView(savedInstanceState: Bundle?) {
        RecyclerViewUtils.initRecyclerView(mRecyclerView, myBaseDataBingAdapter)
        myBaseDataBingAdapter.setOnItemChildClickListener { _, view, position ->
            if (myBaseDataBingAdapter.data.size == 0) return@setOnItemChildClickListener
            when (view.id) {
                R.id.mLinearFavorite -> {
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
                    myBaseDataBingAdapter.remove(position)
                }
            }
            LogUtils.d("myBaseDataBingAdapter${myBaseDataBingAdapter.data.size}")
            LogUtils.d("neiHanViewModel${neiHanViewModel.getAllWorldLiveData().value!!.size}")
        }
    }


}
