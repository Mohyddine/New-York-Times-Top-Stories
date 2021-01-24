package com.mehyo.nyttopstories.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class ViewPagerAdapter(
    list: ArrayList<Fragment>,
    fm:FragmentManager,
    lifecycle: Lifecycle
):FragmentStateAdapter(fm,lifecycle) {

    private val fragmentList :ArrayList<Fragment> = list

    // getting fragments count
    override fun getItemCount(): Int {
        return  fragmentList.size
    }

    // attaching fragments on specific position
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}