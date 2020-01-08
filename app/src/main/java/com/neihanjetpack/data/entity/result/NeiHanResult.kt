package com.neihanjetpack.data.entity.result

import com.google.gson.annotations.Expose
import com.neihanjetpack.base.entity.BaseEntity
import com.neihanjetpack.base.entity.BaseResult


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2020/1/8
 * @Description:
 */
class NeiHanResult : BaseResult() {
    @Expose
    var result = listOf<ResultList>()

    class ResultList : BaseEntity() {
        @Expose
        val text = ""
    }
}