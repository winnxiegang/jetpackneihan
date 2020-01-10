package com.neihanjetpack.data.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myjeptdemo.room.NeiHanLike
import com.neihanjetpack.base.baseview.BaseLifecycleActivity
import com.neihanjetpack.data.entity.result.NeiHanResult
import com.neihanjetpack.data.repository.NeiHanrepository
import kotlinx.coroutines.launch


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2020/1/8
 * @Description:
 */
class NeiHanViewModel(application: Application) : AndroidViewModel(application) {
    private var neiHanRepository: NeiHanrepository = NeiHanrepository(application)
    //todo 创建方法1 LiveData
    val liveData: MutableLiveData<NeiHanResult> = neiHanRepository.liveData

    /**
     * 通过仓库得到收藏的数据
     */
    fun getListData(page: String, type: String) {
        neiHanRepository.getCheckGoodDetal(page, type)
    }

    fun getAllWorldLiveData(): LiveData<List<NeiHanLike>> {
        return neiHanRepository.getAllWorldRepoData()
    }

    fun insertWorlds(world: NeiHanLike) {
        neiHanRepository.insertWorlds(world)
    }


    fun deleteWorlds(world: NeiHanLike) {
        neiHanRepository.deleteWorlds(world)
    }

    fun deleteALL() {
        neiHanRepository.deleteALL()
    }

}