package com.jiutong.base.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by xiangyun_liu on 2017/4/9.
 */

class BaseFragmentStateAdapter(context: FragmentActivity) : FragmentStateAdapter(context) {

    fun add(fragment: Fragment) {
        mFragments.add(fragment)
    }

    private val mFragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}
