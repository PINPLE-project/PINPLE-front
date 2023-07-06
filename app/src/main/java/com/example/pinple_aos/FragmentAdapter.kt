package com.example.pinple_aos

import com.example.pinple_aos.fragments.ReminderListFragment

class FragmentAdapter (fm : FragmentManager): FragmentPagerAdapter(fm) {
    //position 에 따라 원하는 Fragment로 이동시키기
    override fun getItem(position: Int): Fragment {
        val fragment =  when(position)
        {
            0-> ReminderListFragment().newInstant()
            1-> ReminderRecordFragment().newInstant()
            else -> ReminderListFragment().newInstant()
        }
        return fragment
    }

    //tab의 개수만큼 return
    override fun getCount(): Int = 2

    //tab의 이름 fragment마다 바꾸게 하기
    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position)
        {
            0->"0ne"
            1->"Two"
            else -> "main"
        }
        return title     }
}