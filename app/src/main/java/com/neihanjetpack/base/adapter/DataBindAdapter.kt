package com.neihanjetpack.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.neihanjetpack.BR
import com.neihanjetpack.R
import com.neihanjetpack.base.entity.BaseEntity
import com.neihanjetpack.base.utils.DataBinding
import com.neihanjetpack.base.utils.RequestConstant


/**
 * @Author:         xiegang
 * @CreateDate:     2019/8/2 13:09
 * @UpdateRemark:   更新说明：
 */
abstract class DataBindAdapter<R : BaseEntity>(
    layout: Int,
    private val listIdClick: MutableList<Int>? = null) : BaseQuickAdapter<R, DataBindAdapter.DataBindingAdapter>(layout) {
    override fun convert(helper: DataBindingAdapter?, entity: R) {
        helper?.apply {
            entity?.apply {
                helper.binding?.setVariable(BR.listitem, entity)
                listIdClick?.forEach {
                    helper?.addOnClickListener(it)
                }
                binding?.executePendingBindings()
            }
        }
    }

    fun setChangeLoadMore(booleanRefush: Boolean, resultSize: Int, totalSize: Int) {
        this.loadMoreComplete()
        if (this.data.size >= totalSize) {
            this.loadMoreEnd()
            //  LogUtils.d("xg", "${this.data.size}个 1loadMoreEnd")
        } else {
            if (resultSize == RequestConstant.PAGE_SIZE) {
                if (this.data.size < totalSize) {
                    this.loadMoreComplete()
                    //  LogUtils.d("xg", "${this.data.size}个 2loadMoreComplete")
                } else {
                    this.loadMoreEnd()
                    // LogUtils.d("xg", "${this.data.size}个 3loadMoreEnd")
                }
            } else if (resultSize < RequestConstant.PAGE_SIZE) {
                this.loadMoreEnd(booleanRefush)
                // LogUtils.d("xg", "4loadMoreEndbooleanRefush")
            } else {
                this.loadMoreComplete()
                // LogUtils.d("xg", "5loadMoreComplete")
            }
        }

    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(com.neihanjetpack.R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }

    class DataBindingAdapter(view: View) : BaseViewHolder(view) {
        val binding: ViewDataBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
    }
}