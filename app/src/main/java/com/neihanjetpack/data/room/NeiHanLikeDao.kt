package com.example.myjeptdemo.room

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/24
 * @Description:
 */
@Dao
interface NeiHanLikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorlds(neiHanLike: NeiHanLike)

    @Update
    fun updateWorlds(neiHanLike: NeiHanLike)


    @Query("DELETE FROM NeiHanLike")
    fun deleteALL()

    @Delete
    fun deleteWorlds(neiHanLike: NeiHanLike)

    //根据时间倒序查询
    @Query("SELECT * FROM NeiHanLike ORDER BY time DESC")
    //基本获取
    fun getALLWorld(): LiveData<List<NeiHanLike>>
}