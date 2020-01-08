package com.jiutong.base.http

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


/**
 * Created by xiangyun_liu on 2017/9/4.
 * 网络请求接口管理
 *
 * 接口请求定义：
 * http://wiki.9tong.com/pages/viewpage.action?pageId=1409143
 *
 *  get请求 ： ?号后参数参与签名
 *  post表单请求:   表单数据参与签名
 *      json请求：  body内的参数参与签名
 */

interface ApiService {
    companion object {
        /**
         * 接口地址
         */
        val API_ADDRESS = "111"
    }

    /**
     * 获取短信验证码，或者校验图形验证码
     */
    @POST("message/sms/send")
    fun getSMSCode(@Body jCheckGraphCodeEntity: RequestBody): Observable<String>

}

