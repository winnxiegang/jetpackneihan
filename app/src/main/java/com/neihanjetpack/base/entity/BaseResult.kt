package com.neihanjetpack.base.entity

import com.google.gson.annotations.Expose
import com.neihanjetpack.base.entity.BaseEntity

/**
 * 接口返回 java bean 基类
 *
 * @author: xiangyun_liu
 *
 * @date: 2018/8/13 10:23
 */
open class BaseResult : BaseEntity() {
    companion object {
        fun isSuccess(code: Int): Boolean {
            return code == 0
        }
    }


    @Expose
    var code: Int = -1

    @Expose
    var msg = ""

    /**
     * code : 0  成功，其余失败
     */
    fun isSuccess(): Boolean {
        return code == 0
    }

    fun isError(): Boolean {
        return code != 0
    }
}