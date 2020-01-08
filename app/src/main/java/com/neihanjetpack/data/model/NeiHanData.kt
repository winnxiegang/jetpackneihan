package com.neihanjetpack.data.model

import com.google.gson.annotations.Expose
import com.neihanjetpack.base.entity.BaseEntity


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2020/1/8
 * @Description:
 */
class NeiHanData(@Expose val page: Int, @Expose val type: String) : BaseEntity()