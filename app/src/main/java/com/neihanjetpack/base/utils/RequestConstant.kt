package com.neihanjetpack.base.utils

/**
 * 接口请求参数常量
 *
 * @author: xiangyun_liu
 *
 * @date: 2018/8/23 20:06
 */
object RequestConstant {
    /**
     * type  1:App   2:小程序
     */
    const val TYPE_APP = 1

    /**
     * 短信模板  register_logistics_send:物流寄货端注册 register_logistics_sort:物流揽货端注册 register_logistics_sort:物流司机端注册
     */
    const val REGISTER_TEMP_NO = "register_logistics_sort"
    /**
     * 短信模板[forget_logistics_sort:物流揽货端修改密码 forget_logistics_site_ship:物流堆场司机端修改密码
     */
    const val FORGET_TEMP_NO = "forget_logistics_sort"
    /**
     * pageSize
     */
    const val PAGE_SIZE = 10
    /**
     * 待取/好汽配待验货
     */
    const val TYPE_WAIT_GET = 1
    /**
     * 待开单
     */
    const val TYPE_WAIT_BILING = 2
    /**
     * 待发
     */
    const val TYPE_WAIT_SEND = 3
    /**
     * 已完成
     */
    const val TYPE_COMPLETE = 4
    /**
     * 显示的Item个数
     */
    const val SHOW_ITEM_COUNT = 3
}