package com.example.myjeptdemo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.neihanjetpack.base.entity.BaseEntity


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/24
 * @Description:
 *  @Expose val sid: String,
@Expose val text: String,
@Expose val header: String,
@Expose val name: String,
@Expose val forward: String,
@Expose val up: String,
@Expose val comment: String,
@Expose val down: String,
 */
@Entity
data class NeiHanLike(
    @PrimaryKey
    @ColumnInfo(name = "sid") var sid: String = "",
    @ColumnInfo(name = "text") var text: String = "",
    @ColumnInfo(name = "header") var header: String = "",
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "forward") var forward: String = "",
    @ColumnInfo(name = "up") var up: String = "",
    @ColumnInfo(name = "comment") var comment: String = "",
    @ColumnInfo(name = "down") var down: String = "",
    @ColumnInfo(name = "time") var time: Long = System.currentTimeMillis()
)