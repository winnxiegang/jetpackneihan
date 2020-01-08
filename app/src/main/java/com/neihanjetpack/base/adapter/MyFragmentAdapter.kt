package com.jiutong.base.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jiutong.base.constant.Constant

/**
 * Created by xiangyun_liu on 2017/4/9.
 */

class MyFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val mFragments = mutableListOf<Fragment>()

    fun add(fragment: Fragment) {
        mFragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        //此时只是创建了MainFragment的实例，还没有调用OnCreate方法，所以title属性为空，只能使用getArguments方法获取title属性
        val args = getItem(position).arguments
        if (args != null) {
            title = args.getString(Constant.TITLE)
        }
        return title
    }

    override fun getCount(): Int {
        return mFragments.size
    }
}
