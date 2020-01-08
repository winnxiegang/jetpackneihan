package com.jiutong.base.http

import java.io.IOException

/**
 * 自定义HttpErrorException
 *
 * @author: xiangyun_liu
 *
 * @date: 2019/1/2 18:27
 */
class HttpMyErrorException(val code: Int, override val message: String) : IOException(message) {
    companion object {
        const val ERROR_SYSTEM = -99999
    }
}