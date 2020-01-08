package com.neihanjetpack.base.adapter

import com.neihanjetpack.base.entity.BaseEntity


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/8/2
 * @Description: 所有基本数据DataBing的适配器
 */
class MyBaseDataBingAdapter<R : BaseEntity>(layout: Int, listIdClick: MutableList<Int>? = null)
    : DataBindAdapter<R>(layout, listIdClick)