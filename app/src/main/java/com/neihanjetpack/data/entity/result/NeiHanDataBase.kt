package com.neihanjetpack.data.entity.result

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/24
 * @Description: todo 统一数据库存放位置[World::class] 里面可放多个
 */
@Database(entities = [NeiHanResultItem::class], version = 1, exportSchema = false)
abstract class NeiHanDataBase : RoomDatabase() {
    abstract fun getALlWorldDao(): NeiHanDao  // 用于得到所有的单词

    companion object {
        @Volatile
        private var instence: NeiHanDataBase? = null

        fun getInstence(context: Context): NeiHanDataBase {
            if (instence == null) {
                synchronized(NeiHanDataBase::class) {
                    if (instence == null) {
                        instence = Room.databaseBuilder(
                            context.applicationContext,
                            NeiHanDataBase::class.java,
                            "world_data_base"
                        )
                            .build()
                    }
                }
            }
            return instence!!
        }
    }
}