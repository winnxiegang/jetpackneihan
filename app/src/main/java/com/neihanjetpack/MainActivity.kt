package com.neihanjetpack

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.jiutong.base.adapter.BaseFragmentStateAdapter
import com.neihanjetpack.base.baseview.BaseActivity
import com.neihanjetpack.databinding.ActivityMainBinding
import com.neihanjetpack.fragmrent.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val baseFragmentStateAdapter by lazy { BaseFragmentStateAdapter(this) }


    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }

    override fun initView(savedInstanceState: Bundle?) {
        baseFragmentStateAdapter.add(TextFragment())
        baseFragmentStateAdapter.add(ImageFragment())
        baseFragmentStateAdapter.add(GifFragment())
        baseFragmentStateAdapter.add(VideoFragment())
        baseFragmentStateAdapter.add(LikeFragment())
        mViewPager.adapter = baseFragmentStateAdapter
        //        TabLayout和ViewPager的绑定
        TabLayoutMediator(mTabLayout, mViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "文字"
                1 -> tab.text = "图片"
                2 -> tab.text = "动图"
                3 -> tab.text = "视频"
                4 -> tab.text = "喜欢"
            }
        }.attach()
    }

}
