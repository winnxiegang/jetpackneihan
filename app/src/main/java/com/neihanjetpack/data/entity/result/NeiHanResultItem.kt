package com.neihanjetpack.data.entity.result

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.neihanjetpack.base.entity.BaseEntity
import com.neihanjetpack.base.entity.BaseResult


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2020/1/8
 * @Description: romm 只能放单个对象  NeiHanResult.@ResultList
 */
@Entity
class NeiHanResultItem(
    @ColumnInfo(name = "text2")  val text: String,
    @ColumnInfo(name = "header2")  val header: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
