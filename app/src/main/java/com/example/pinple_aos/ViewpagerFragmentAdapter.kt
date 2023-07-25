package com.example.pinple_aos

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pinple_aos.Landing_fragments.Fragment01
import com.example.pinple_aos.Landing_fragments.Fragment02
import com.example.pinple_aos.Landing_fragments.Fragment03

class ViewpagerFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 3 // 세 개의 프래그먼트를 반환
    }

    override fun createFragment(position: Int): Fragment {
        // 각 위치에 따라 다른 프래그먼트 반환
        return when (position) {
            0 -> Fragment01()
            1 -> Fragment02()
            2 -> Fragment03()
            else -> throw IllegalStateException("Invalid position $position")
        }
    }
}