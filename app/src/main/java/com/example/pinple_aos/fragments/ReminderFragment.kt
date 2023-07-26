package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pinple_aos.R
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.example.pinple_aos.FragmentAdapter

class ReminderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reminder, container, false)

        val pagerAdapter = FragmentAdapter(childFragmentManager)
        val pager = view.findViewById<ViewPager>(R.id.viewPager)
        pager.adapter = pagerAdapter

        val tab = view.findViewById<TabLayout>(R.id.Tab)
        tab.setupWithViewPager(pager)

        tab.tabMode = TabLayout.MODE_FIXED

        val tabCount = tab.tabCount
        for (i in 0 until tabCount) {
            val tabItem = tab.getTabAt(i)
            tabItem?.let {
                it.view.minimumWidth = 0  // 최소 너비를 0으로 설정하여 고정
            }
        }

        return view
    }

}
