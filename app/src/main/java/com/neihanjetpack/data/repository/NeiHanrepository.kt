package com.neihanjetpack.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.administrator.wanandroid.utils.runOnIoThread
import com.example.myjeptdemo.room.NeiHanLike
import com.example.myjeptdemo.room.WorldDataBase
import com.neihanjetpack.base.baseview.BaseLifecycleActivity
import com.neihanjetpack.base.http.ApiClient
import com.neihanjetpack.base.http.RxTransformer
import com.neihanjetpack.base.utils.ToastUtil
import com.neihanjetpack.data.entity.result.NeiHanResult
import com.trello.rxlifecycle2.RxLifecycle.bindUntilEvent
import com.trello.rxlifecycle2.android.ActivityEvent


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2020/1/8
 * @Description:
 */
class NeiHanrepository(context: Context) {
     var liveData = MutableLiveData<NeiHanResult>()
    private var worldDaoLiveData = WorldDataBase.getInstence(context).getALlWorldDao()
    private var allWorldLiveData: LiveData<List<NeiHanLike>>
    /**
     * 获取网络请求数据
     */
    fun getCheckGoodDetal(page: String, type: String) {
        ApiClient.mApiServer.getData(page, type)
            .compose(RxTransformer.io2Main())
            .subscribe({
                liveData.value = it
            }, {})
    }


    init {
        allWorldLiveData = worldDaoLiveData.getALLWorld()
    }

    fun getAllWorldRepoData(): LiveData<List<NeiHanLike>> {
        return allWorldLiveData
    }

    fun insertWorlds(world: NeiHanLike) {
        ToastUtil.toast("insertWorlds")
        runOnIoThread { worldDaoLiveData.insertWorlds(world) }
    }


    fun deleteWorlds(world: NeiHanLike) {
        ToastUtil.toast("deleteWorlds${world.sid}")
        runOnIoThread { worldDaoLiveData.deleteWorlds(world) }
    }

    fun deleteALL() {
        runOnIoThread { worldDaoLiveData.deleteALL() }
    }
}