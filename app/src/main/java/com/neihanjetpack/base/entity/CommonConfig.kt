package com.neihanjetpack.base.entity

import com.google.gson.Gson
import org.litepal.crud.LitePalSupport

open class CommonConfig : LitePalSupport() {

    companion object {

        fun <T> fromJson(json: String, clazz: Class<T>): T {
            return Gson().fromJson(json, clazz)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}