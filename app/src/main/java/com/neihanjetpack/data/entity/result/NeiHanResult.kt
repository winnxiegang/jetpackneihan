package com.neihanjetpack.data.entity.result

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.neihanjetpack.base.entity.BaseEntity
import com.neihanjetpack.base.entity.BaseResult
import org.litepal.crud.LitePalSupport


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2020/1/8
 * @Description: forward
 */
@Entity
class NeiHanResult : BaseResult() {
    @Expose
    var result = listOf<ResultList>()

    @Entity
    class ResultList(

        @Expose val sid: String,
        @Expose val text: String,
        @Expose val header: String,
        @Expose val name: String,
        @Expose val forward: String,
        @Expose val up: String,
        @Expose val comment: String,
        @Expose val down: String,
        @Expose var isLike: Boolean = false//使用databing 必须赋值 不然报错
    ) : BaseEntity()
}