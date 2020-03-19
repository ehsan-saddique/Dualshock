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
            0 ->
                return MainFragment.newInstance(DTOItem("MAGMA RED", "$64.00", R.mipmap.img_dualshock_1, R.color.colorBackground1, position))

            else -> return MainFragment.newInstance(DTOItem("WAVE BLUE", "$54.00", R.mipmap.img_dualshock_2, R.color.colorBackground2, position))
        }
    }

    override fun getCount(): Int {
        return 2
    }

}