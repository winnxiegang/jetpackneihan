package com.neihanjetpack.base.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.neihanjetpack.base.entity.BaseEntity
import com.neihanjetpack.base.utils.RequestConstant


/**
 * @Author:         xiegang
 * @CreateDate:     2019/8/2 13:09
 * @UpdateRemark:   更新说明：
 */
open class MyBaseRecyclerAdapter<R : BaseEntity>(layout: Int) :
    BaseQuickAdapter<R, BaseViewHolder>(layout) {
    override fun convert(helper: BaseViewHolder?, entity: R) {}
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
}