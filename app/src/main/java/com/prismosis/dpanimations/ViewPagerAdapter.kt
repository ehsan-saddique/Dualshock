package com.prismosis.dpanimations

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter



/**
 * Created by Ehsan Saddique on 2020-03-17
 */

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MainFragment.newInstance(Color.parseColor("#F44336"), position) // red
            else -> return MainFragment.newInstance(Color.parseColor("#5378D2"), position) // blue
        }
    }

    override fun getCount(): Int {
        return 2
    }

}