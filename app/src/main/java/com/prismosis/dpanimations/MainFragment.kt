package com.prismosis.dpanimations

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View


/**
 * Created by Ehsan Saddique on 2020-03-17
 */

class MainFragment : Fragment() {

    private var mBackgroundColor: Int = 0
    private var mPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!arguments!!.containsKey(BACKGROUND_COLOR))
            throw RuntimeException("Fragment must contain a \"$BACKGROUND_COLOR\" argument!")
        mBackgroundColor = arguments!!.getInt(BACKGROUND_COLOR)

        if (!arguments!!.containsKey(PAGE))
            throw RuntimeException("Fragment must contain a \"$PAGE\" argument!")
        mPage = arguments!!.getInt(PAGE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Select a layout based on the current page
        val layoutResId: Int
        when (mPage) {
            0 -> layoutResId = R.layout.fragment_layout_1
            else -> layoutResId = R.layout.fragment_layout_2
        }

        // Inflate the layout resource file
        val view = activity?.layoutInflater?.inflate(layoutResId, container, false)

        // Set the current page index as the View's tag (useful in the PageTransformer)
        view?.tag = mPage

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the background color of the root view to the color specified in newInstance()
//        val background = view.findViewById<View>(R.id.intro_background)
//        background.setBackgroundColor(mBackgroundColor)
    }

    companion object {

        private val BACKGROUND_COLOR = "backgroundColor"
        private val PAGE = "page"

        fun newInstance(backgroundColor: Int, page: Int): MainFragment {
            val frag = MainFragment()
            val b = Bundle()
            b.putInt(BACKGROUND_COLOR, backgroundColor)
            b.putInt(PAGE, page)
            frag.setArguments(b)
            return frag
        }
    }

}