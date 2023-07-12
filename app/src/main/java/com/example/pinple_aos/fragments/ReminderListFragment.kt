package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pinple_aos.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import android.widget.FrameLayout
import android.widget.TextView

class ReminderListFragment : Fragment() {

    companion object {
        fun newInstance(): ReminderListFragment {
            val args = Bundle()
            val fragment = ReminderListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reminder_list, container, false)
    }
}

