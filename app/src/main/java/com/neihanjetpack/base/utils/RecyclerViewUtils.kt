package com.neihanjetpack.base.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.neihanjetpack.MyApplication
import com.neihanjetpack.R


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/8/21
 * @Description: RecyclerView 类型的封装
 */
object RecyclerViewUtils {
    fun initRecyclerView(mRecyclerView: RecyclerView, adapter: BaseQuickAdapter<*, *>) {
        mRecyclerView.layoutManager = LinearLayoutManager(MyApplication.getInstance())
        mRecyclerView.adapter = adapter
        val view: View = LayoutInflater.from(MyApplication.getInstance().applicationContext)
            .inflate(R.layout.layout_none_emptyview, mRecyclerView.parent as ViewGroup, false)
        adapter.emptyView = view
    }
}