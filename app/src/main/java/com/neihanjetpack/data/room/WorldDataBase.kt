package com.example.myjeptdemo.room

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
@Database(entities = [NeiHanLike::class], version = 2, exportSchema = false)
abstract class WorldDataBase : RoomDatabase() {
    abstract fun getALlWorldDao(): NeiHanLikeDao  // 用于得到所有的单词

    companion object {
        @Volatile
        private var instence: WorldDataBase? = null

        fun getInstence(context: Context): WorldDataBase {
            if (instence == null) {
                synchronized(WorldDataBase::class) {
                    if (instence == null) {
                        instence = Room.databaseBuilder(
                            context.applicationContext,
                            WorldDataBase::class.java,
                            "world_data_base"
                        )
                            // .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return instence!!
        }
    }
}