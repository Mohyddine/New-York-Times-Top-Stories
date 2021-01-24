package com.mehyo.nyttopstories.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mehyo.nyttopstories.R
import com.mehyo.nyttopstories.ui.adapter.ViewPagerAdapter
import com.mehyo.nyttopstories.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {
    private var _binding: FragmentViewPagerBinding?= null
    private val binding get() = _binding!!

    lateinit var viewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentViewPagerBinding.inflate(inflater, container, false)

        //fragment List
        val fragmentList= arrayListOf<Fragment> (
            TopStoriesFragment(),
            BookmarksFragment()
        )

        //Initialize ViewPager adapter
        val adapter = ViewPagerAdapter(
        fragmentList,
        requireActivity().supportFragmentManager,
        lifecycle
        )

        //set binding ViewPager adapter
        binding.ViewPager.adapter=adapter

        //set titles and icons of the tablayout
        TabLayoutMediator(binding.TabLayout,binding.ViewPager){tab,position->
            when(position){
                0-> {
                    tab.text = "Top Stories"
                    tab.icon=resources.getDrawable(R.drawable.ic_topstories,null)
                }

                1-> {
                    tab.text = "Bookmarks"
                    tab.icon=resources.getDrawable(R.drawable.ic_bookmarks,null)
                }
            }

        }.attach()

        return binding.root
    }

    //clear binding after closing the fragment
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}