package com.neihanjetpack.data.entity.result

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/24
 * @Description:
 */
@Dao
interface NeiHanDao {
    @Insert
    fun insertNeiHan(world: NeiHanResultItem)

    @Update
    fun updateNeiHans(world: NeiHanResultItem)

    @Delete
    fun deleteNeiHans(world: NeiHanResultItem)

    @Query("DELETE FROM NeiHanResultItem")
    fun deleteALL()

    @Query("SELECT * FROM NeiHanResultItem ORDER BY ID DESC")
    //基本获取
    fun getALLNeiHan(): LiveData<List<NeiHanResultItem>>
}