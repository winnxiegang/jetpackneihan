package com.jiutong.base.constant

/**
 * 网络常量
 *
 * @author: xiangyun_liu
 * @date: 2018/8/3 12:05
 */
object HttpConstant {
    /**
     * http配置参数
     */
    object Config {
        /**
         * 连接超时
         */
        const val CONNECT_TIME_OUT = 30L
        /**
         * 读取超时
         */
        const val READ_TIME_OUT = 30L
        /**
         * 写入超时间
        const   */
        const val WRITE_TIME_OUT = 30L
        /**
         * 缓存文件目录
         */
        val CACHE_DIRECTORY = "okHttp_cache"
        /**
         * 缓存大小
         */
        val CACHE_MAX_SIZE = 100 * 1024 * 1024L
    }


    /**
     * http 请求头
     */
    object Header {
        /**
         * 5 揽货员app
         */
        const val APP_ID = "appId"
        /**
         * 1：H5  2：Android  3：IOS
         */
        const val PLATFORM_ID = "platformId"
        /**
         * 授权凭证
         */
        const val TOKEN = "token"
        /**
         * 时间戳
         */
        const val TIME_STAMP = "timestamp"
        /**
         * 签名
         */
        const val SIGN = "sign"
        /**
         * userId
         */
        const val USER_ID = "userId"
        /**
         * 程序版本号
         */
        const val VERSION = "version"
        /**
         * 设备唯一标识
         */
        const val MACHINE_NO = "machineNo"
        /**
         * 本地秘钥
         */
        const val KEY = "key"
        /**
         * 秘钥 debug(非线上环境)
         */
        const val SECRET_KEY_DEBUG = "secretKey-android"
        /**
         * 秘钥 release(线上环境)
         */
        const val SECRET_KEY_RELEASE = "3465b3ba-f92d-4dd2-81c8-ebcc340615cd"
    }


    /**
     * 固定请求值
     */
    object RequestParam {
        /**
         * 揽货员app :5
         */
        const val APP_ID_PARAM = "12"
        /**
         * android :2
         */
        const val PLATFORM_ID_PARAM = "2"
    }
}