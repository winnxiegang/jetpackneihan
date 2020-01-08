package com.neihanjetpack.base.http

/**
 * 响应错误码常量
 *
 * @author: xiangyun_liu
 *
 * @date: 2018/8/23 20:26
 */
object ResponseCodeConstant {
    /**
     * 获取短信验证码错误
     */
    const val ERROR_SMS_CODE = 30010001

    /**
     * 用户已被封禁
     */
    const val ERROR_BANNED_CODE = 10010003

    /**
     * 用户未登陆
     */
    const val ERROR_NO_LOGIN_IN = 1003

    /**
     * 无效的token
     */
    const val ERROR_INVALID_TOKEN_CODE = 10010002

    /**
     * 缺少token
     */
    const val ERROR_LACK_TOKEN_CODE = 10010005
    /**
     * 拒绝访问
     */
    const val ERROR_ACCESS_DENIED = 900
}